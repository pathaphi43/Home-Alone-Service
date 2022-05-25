package com.csproject.homealoneservice.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;



@org.springframework.context.annotation.Configuration

@PropertySource("classpath:application.properties")
public class Configuration {





    @Value("${homealone.ftp.host}")
    private String FTPHost;

    @Value("${homealone.ftp.port}")
    private int FTPPort;

    @Value("${homealone.ftp.username}")
    private String FTPUsername;

    @Value("${homealone.ftp.password}")
    private String FTPPassword;
    @Value("${templatePath}")
    private String templatePath;

    @Value("${reportPath}")
    private String reportPath;

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }


    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getFTPHost() {
        return FTPHost;
    }

    public void setFTPHost(String FTPHost) {
        this.FTPHost = FTPHost;
    }

    public int getFTPPort() {
        return FTPPort;
    }

    public void setFTPPort(int FTPPort) {
        this.FTPPort = FTPPort;
    }

    public String getFTPUsername() {
        return FTPUsername;
    }

    public void setFTPUsername(String FTPUsername) {
        this.FTPUsername = FTPUsername;
    }

    public String getFTPPassword() {
        return FTPPassword;
    }

    public void setFTPPassword(String FTPPassword) {
        this.FTPPassword = FTPPassword;
    }
}
