package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.configurations.Configuration;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.SizeLimitExceededException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileUpload {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    Configuration configuration;

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

    public ResponseEntity<UploadFileDTO> uploadRentPdf(MultipartFile file) {
        String url = "http://homealone.comsciproject.com/manager/upload/pdf";
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
            params.add("pdf_file", new FileSystemResource(convFile));
        } catch (Exception e) {

            logger.info("File upload error:"+e.getClass());
            if(e.getClass() == SizeLimitExceededException.class){
                logger.info(e.getClass());
            }
            e.printStackTrace();

        }

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        Map<String, Object> FeedBackStatus=new HashMap<String, Object>();
        ResponseEntity<UploadFileDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, UploadFileDTO.class);
        return response;
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
