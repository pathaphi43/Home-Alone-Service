package com.csproject.homealoneservice.service;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.csproject.homealoneservice.configurations.Configuration;
import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.SizeLimitExceededException;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileUpload {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    Configuration configuration;

    private static Date queryDate;
    private String formatDate = "yyyyMMdd";
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ZoneId zone = ZoneId.of("Asia/Bangkok");
    private ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Bangkok"));
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd-HHmmss").withZone(zone);
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(zone);


    public ResponseEntity<UploadFileDTO> uploadProfile(MultipartFile file) {
        String url = "http://homealone.comsciproject.com/manager/upload/profile";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        try {
            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            //***Converd File
            params.add("image_file", new FileSystemResource(convFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        Map<String, Object> FeedBackStatus=new HashMap<String, Object>();
        ResponseEntity<UploadFileDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, UploadFileDTO.class);
        return response;
    }

    public ResponseEntity<UploadFileDTO> uploadRentPdf(MultipartFile file, RentDTO rentBody) {
        String url = "http://homealone.comsciproject.com/manager/upload/pdf";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("pdf_file", new FileSystemResource(converdFilePdf(file,rentBody)));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        Map<String, Object> FeedBackStatus=new HashMap<String, Object>();
        ResponseEntity<UploadFileDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, UploadFileDTO.class);
        return response;
    }

    public File converdFilePdf(MultipartFile file,RentDTO rentBody){
        try {
            File convFile = new File(file.getOriginalFilename());
            logger.info(convFile.getAbsolutePath());
            logger.info(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            File  renameFile = new File(zdt.toLocalDateTime().format(dateTimeFormatter)+"-"+rentBody.getRid()+rentBody.getTid()
                    +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            logger.info(renameFile);
            FileOutputStream fos = new FileOutputStream(renameFile);
            fos.write(file.getBytes());
            fos.close();

            //***Converd File
            return renameFile;
        } catch (Exception e) {
            logger.info("File upload error:"+e.getClass());
            if(e.getClass() == SizeLimitExceededException.class){
                logger.info(e.getClass());
            }
            e.printStackTrace();
                return null;
        }

    }


    public List<MultipartFile> handleFileUpload(List<MultipartFile> file) {
        System.out.println("File Size "+file);
        try {
            FTPClient con = new FTPClient();
//            con.setConnectTimeout(60000);


            System.out.println("Connecting:"+con.getReplyString());
            System.out.println(configuration.getFTPHost()+":"+configuration.getFTPPort() +"\n"+ configuration.getFTPUsername() + configuration.getFTPPassword());
            con.connect(configuration.getFTPHost(),configuration.getFTPPort());
            System.out.println("Reply :"+con.getReplyString());
//            System.out.println("TIME :"+config.getServerTimeZoneId());
            con.login(configuration.getFTPUsername(),configuration.getFTPPassword());
            if (con.isConnected()) {
                con.enterLocalPassiveMode(); // important!
                System.out.println("Connected");
                con.setFileType(FTP.BINARY_FILE_TYPE);
                for (MultipartFile files : file) {
                    boolean result = con.storeFile("img/" + files.getOriginalFilename(), files.getInputStream());
                }
                con.logout();
                con.disconnect();
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + file.getOriginalFilename() + "!");
                return file;
            }else {

                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
//            redirectAttributes.addFlashAttribute("message",
//                    "Could not upload " + file.getOriginalFilename() + "!");
            System.out.println("Can't Connect");
            return null;
        }

    }
}
