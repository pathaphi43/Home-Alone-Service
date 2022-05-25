package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.configurations.Configuration;
import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.PaymentRepository;
import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.dto.DataListDTO;
import com.csproject.homealoneservice.dto.PaymentSummaryDTO;
import com.csproject.homealoneservice.dto.PaymentSummaryReportDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.enumeration.PayStatusEnum;
import com.csproject.homealoneservice.enumeration.ReportTemplate;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.DataLine;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class GenerateReportService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private List<String> srcFiles;
    private static Date queryDate;
    private String formatDate = "yyyyMMdd";

    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
    ZoneId zone = ZoneId.of("Asia/Bangkok");
    ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Bangkok"));

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    @Autowired
    Configuration configuration;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    RentingHouseRepository rentingHouseRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    ManagerRepository managerRepository;


    public String exportPdf(Integer mid,String dateFrom,String dateTo) {
        StringBuilder outputPath = new StringBuilder();
        outputPath.append("/");
        outputPath.append(configuration.getReportPath());
        outputPath.append("/");
        outputPath.append("teacher-list");
        outputPath.append(ReportTemplate.SUMMARY_PDF.getType());
        logger.info("setup output file name success");

        JasperPrint reportTemplate = this.generatePdfReportTemplate(mid,dateFrom,dateTo);
        logger.info("PDF template print teacher id " + mid + " success");

        generatePdfReport(reportTemplate, outputPath.toString());
        logger.info("PDF file teacher id " + mid + " generate success");

        return outputPath.toString();

    }

    JasperPrint generatePdfReportTemplate(int mid,String dateFrom,String dateTo) {
        try {
            String sourceFile = configuration.getTemplatePath() + ReportTemplate.SUMMARY_PDF.getTemplateFile();
            JasperReport jasperReport = JasperCompileManager.compileReport(sourceFile);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paymentSummary(mid,dateFrom,dateTo));
            Map<String, Object> param = new HashMap<>();
            param.put("createdBy", "HOMEALONE");
            return JasperFillManager.fillReport(jasperReport, param, dataSource);
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PaymentSummaryReportDTO> paymentSummary(int mid, String dateFrom, String dateTo){
        List<HouseEntity> houses = houseRepository.findByMid(mid);
        ManagerEntity manager = managerRepository.findById(mid).get();
        Timestamp start = Timestamp.valueOf(dateFrom);
        Timestamp end = Timestamp.valueOf(dateTo);
        List<PaymentSummaryReportDTO> payments = new ArrayList<>();
        List<DataListDTO> listDTO = new ArrayList<>();
        for(HouseEntity house:houses){
            List<RentingHouseEntity>  rentings = rentingHouseRepository.findByHid(house.getHid());
            for (RentingHouseEntity renting:rentings){
                List<PaymentEntity> payment =   paymentRepository.findAllByRidAndPayHouseStatusAndInstallmentBetween(renting.getRid(), PayStatusEnum.Success_Status.getStatus(),start,end);
                if(!payment.isEmpty()){
                    for(PaymentEntity pay: payment){
                       listDTO.add(new DataListDTO(house.getHouseName(),pay.getInstallment().toString(),pay.getPayHouseAmount()+" บาท"));
                    }
                }
            }
        }
        payments.add( new PaymentSummaryReportDTO(dateFrom,dateTo,manager.getManagerFirstname(),manager.getManagerLastname(), zdt.toLocalDateTime().toString(),listDTO));
        return payments;
    }

    void generatePdfReport(JasperPrint template, String outputPath) {
        try {
            File file = new File(Paths.get("").toAbsolutePath() + outputPath);
            JasperExportManager.exportReportToPdfFile(template, file.getPath());
            logger.info("generate file " + outputPath + " success");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
