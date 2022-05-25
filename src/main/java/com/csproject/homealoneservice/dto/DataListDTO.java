package com.csproject.homealoneservice.dto;

public class DataListDTO {
        private String houseName;
        private String installment;
        private String payHouseAmount;

    public DataListDTO() {
    }

    public DataListDTO(String houseName, String installment, String payHouseAmount) {
        this.houseName = houseName;
        this.installment = installment;
        this.payHouseAmount = payHouseAmount;
    }

    public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getInstallment() {
            return installment;
        }

        public void setInstallment(String installment) {
            this.installment = installment;
        }

        public String getPayHouseAmount() {
            return payHouseAmount;
        }

        public void setPayHouseAmount(String payHouseAmount) {
            this.payHouseAmount = payHouseAmount;
        }

}
