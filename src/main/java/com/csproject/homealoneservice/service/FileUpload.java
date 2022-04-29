package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.configurations.Configuration;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileUpload {

    @Autowired
    Configuration configuration;

    public List<MultipartFile> handleFileUpload(List<MultipartFile> file) {

//        String FTP_ADDRESS = configuration.getFTPHost();
//        int port = configuration.getFTPPort();
//        String LOGIN = configuration.getFTPUsername();
//        String PSW = configuration.getFTPPassword();
        FTPClientConfig config = new FTPClientConfig();


        System.out.println("File Size "+file);
        try {
            FTPClient con = new FTPClient();
            con.setConnectTimeout(60000);
            con.getReplyString();

            System.out.println("Connecting");
            System.out.println(configuration.getFTPHost()+":"+configuration.getFTPPort() +"\n"+ configuration.getFTPUsername() + configuration.getFTPPassword());
            con.connect(configuration.getFTPHost(),configuration.getFTPPort());
            System.out.println("Reply :"+con.getReplyString());
            System.out.println("TIME :"+config.getServerTimeZoneId());
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
//            redirectAttributes.addFlashAttribute("message",
//                    "Could not upload " + file.getOriginalFilename() + "!");
            System.out.println("Can't Connect");
            return null;
        }

    }
}
