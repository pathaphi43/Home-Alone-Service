package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.configurations.Configuration;
import com.csproject.homealoneservice.entity.ManagerEntity;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.MBeanServerConnection;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.io.File;
import java.nio.channels.Channel;


@RestController
@RequestMapping(value = "/file")
public class FileUploadController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    Configuration configuration;

    @GetMapping("")
    public String show(){
        return "FileUploadController";
    };


//    private FakeFtpServer fakeFtpServer;
//
//
//    private FtpClient ftpClient;
//
//    @Before
//    public void setup() throws IOException {
//        fakeFtpServer = new FakeFtpServer();
//        fakeFtpServer.addUserAccount(new UserAccount("user", "password", "/data"));
//
//        FileSystem fileSystem = new UnixFakeFileSystem();
//        fileSystem.add(new DirectoryEntry("/data"));
//        fileSystem.add(new FileEntry("/data/foobar.txt", "abcdef 1234567890"));
//        fakeFtpServer.setFileSystem(fileSystem);
//        fakeFtpServer.setServerControlPort(0);
//
//        fakeFtpServer.start();
//
//        ftpClient = new FtpClient("localhost", fakeFtpServer.getServerControlPort(), "user", "password");
//        ftpClient.open();
//    }
//
//    @After
//    public void teardown() throws IOException {
//        ftpClient.close();
//        fakeFtpServer.stop();
//    }


    @PostMapping(value  ="/upload",consumes = "multipart/form-data")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile[] file) {

//        String FTP_ADDRESS = configuration.getFTPHost();
//        int port = configuration.getFTPPort();
//        String LOGIN = configuration.getFTPUsername();
//        String PSW = configuration.getFTPPassword();


        System.out.println("File Size "+file.length);
        try {
            FTPClient con = new FTPClient();
            con.setConnectTimeout(60000);
            logger.info("Connecting");
            con.connect(configuration.getFTPHost(),configuration.getFTPPort());
            con.login(configuration.getFTPUsername(),configuration.getFTPPassword());
            if (con.isConnected()) {
                logger.info("Connected");
                con.enterLocalPassiveMode(); // important!

                con.setFileType(FTP.BINARY_FILE_TYPE);
                for (MultipartFile files : file) {
                    boolean result = con.storeFile("img/" + files.getOriginalFilename(), files.getInputStream());
                }
                con.logout();
                con.disconnect();
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + file.getOriginalFilename() + "!");
                return new ResponseEntity<String>("You successfully uploaded " + file.length + "!", HttpStatus.OK);
            }else {
                logger.info("Can not connect!!");
                return new ResponseEntity<String>("Can not connect!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("message",
//                    "Could not upload " + file.getOriginalFilename() + "!");
            logger.info("Could not upload"+e);
            return new ResponseEntity<String>("Could not upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}