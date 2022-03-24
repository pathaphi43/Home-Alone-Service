package com.csproject.homealoneservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "tenant", schema = "comsci_homealone", catalog = "")
public class TenantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tid")
    private int tid;
    @Basic
    @Column(name = "tenant_username")
    private String tenantUsername;
    @Basic
    @Column(name = "tenant_password")
    private String tenantPassword;
    @Basic
    @Column(name = "tenant_firstname")
    private String tenantFirstname;
    @Basic
    @Column(name = "tenant_lastname")
    private String tenantLastname;
    @Basic
    @Column(name = "tenant_phone")
    private String tenantPhone;
    @Basic
    @Column(name = "tenant_id_card")
    private String tenantIdCard;
    @Basic
    @Column(name = "tenant_address")
    private String tenantAddress;
    @Basic
    @Column(name = "tenant_province")
    private String tenantProvince;
    @Basic
    @Column(name = "tenant_district")
    private String tenantDistrict;
    @Basic
    @Column(name = "tenant_email")
    private String tenantEmail;
    @Basic
    @Column(name = "tenant_image")
    private String tenantImage;
    @Basic
    @Column(name = "tenant_status")
    private int tenantStatus;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTenantUsername() {
        return tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }

    public String getTenantPassword() {
        return tenantPassword;
    }

    public void setTenantPassword(String tenantPassword) {
        this.tenantPassword = tenantPassword;
    }

    public String getTenantFirstname() {
        return tenantFirstname;
    }

    public void setTenantFirstname(String tenantFirstname) {
        this.tenantFirstname = tenantFirstname;
    }

    public String getTenantLastname() {
        return tenantLastname;
    }

    public void setTenantLastname(String tenantLastname) {
        this.tenantLastname = tenantLastname;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public String getTenantIdCard() {
        return tenantIdCard;
    }

    public void setTenantIdCard(String tenantIdCard) {
        this.tenantIdCard = tenantIdCard;
    }

    public String getTenantAddress() {
        return tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress;
    }

    public String getTenantProvince() {
        return tenantProvince;
    }

    public void setTenantProvince(String tenantProvince) {
        this.tenantProvince = tenantProvince;
    }

    public String getTenantDistrict() {
        return tenantDistrict;
    }

    public void setTenantDistrict(String tenantDistrict) {
        this.tenantDistrict = tenantDistrict;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public String getTenantImage() {
        return tenantImage;
    }

    public void setTenantImage(String tenantImage) {
        this.tenantImage = tenantImage;
    }

    public int getTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(int tenantStatus) {
        this.tenantStatus = tenantStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenantEntity that = (TenantEntity) o;

        if (tid != that.tid) return false;
        if (tenantStatus != that.tenantStatus) return false;
        if (tenantUsername != null ? !tenantUsername.equals(that.tenantUsername) : that.tenantUsername != null)
            return false;
        if (tenantPassword != null ? !tenantPassword.equals(that.tenantPassword) : that.tenantPassword != null)
            return false;
        if (tenantFirstname != null ? !tenantFirstname.equals(that.tenantFirstname) : that.tenantFirstname != null)
            return false;
        if (tenantLastname != null ? !tenantLastname.equals(that.tenantLastname) : that.tenantLastname != null)
            return false;
        if (tenantPhone != null ? !tenantPhone.equals(that.tenantPhone) : that.tenantPhone != null) return false;
        if (tenantIdCard != null ? !tenantIdCard.equals(that.tenantIdCard) : that.tenantIdCard != null) return false;
        if (tenantAddress != null ? !tenantAddress.equals(that.tenantAddress) : that.tenantAddress != null)
            return false;
        if (tenantProvince != null ? !tenantProvince.equals(that.tenantProvince) : that.tenantProvince != null)
            return false;
        if (tenantDistrict != null ? !tenantDistrict.equals(that.tenantDistrict) : that.tenantDistrict != null)
            return false;
        if (tenantEmail != null ? !tenantEmail.equals(that.tenantEmail) : that.tenantEmail != null) return false;
        if (tenantImage != null ? !tenantImage.equals(that.tenantImage) : that.tenantImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + (tenantUsername != null ? tenantUsername.hashCode() : 0);
        result = 31 * result + (tenantPassword != null ? tenantPassword.hashCode() : 0);
        result = 31 * result + (tenantFirstname != null ? tenantFirstname.hashCode() : 0);
        result = 31 * result + (tenantLastname != null ? tenantLastname.hashCode() : 0);
        result = 31 * result + (tenantPhone != null ? tenantPhone.hashCode() : 0);
        result = 31 * result + (tenantIdCard != null ? tenantIdCard.hashCode() : 0);
        result = 31 * result + (tenantAddress != null ? tenantAddress.hashCode() : 0);
        result = 31 * result + (tenantProvince != null ? tenantProvince.hashCode() : 0);
        result = 31 * result + (tenantDistrict != null ? tenantDistrict.hashCode() : 0);
        result = 31 * result + (tenantEmail != null ? tenantEmail.hashCode() : 0);
        result = 31 * result + (tenantImage != null ? tenantImage.hashCode() : 0);
        result = 31 * result + tenantStatus;
        return result;
    }
}
