package com.csproject.homealoneservice.enumeration;

public enum ReportTemplate {
    SUMMARY_PDF("/summary-report-pdf.jrxml",".pdf"),
    ;

    private String templateFile;
    private String type;



    ReportTemplate(String template, String type) {
        this.templateFile = template;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }
}
