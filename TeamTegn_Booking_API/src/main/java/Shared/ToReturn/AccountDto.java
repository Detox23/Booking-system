package Shared.ToReturn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class AccountDto {
    private int id;

    private String accountName;

    private int accountTypeID;

    private Integer parentId;

    private Integer primaryContactId;

    private Integer departmentId;

    private List<String> ean;

    private String telephoneCode;

    private String telephoneNumber;

    private String faxCode;

    private String faxNumber;

    private String website;

    private String cvrNumber;

    private String street;

    private String postcode;

    private String city;

    private String stateRegion;

    private String country;

    private int createdBy;

    private String email;

    private String contactName;

    private String contactEmail;

    private String contactTelephone;

    @JsonFormat(timezone ="Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdDate;
    @JsonFormat(timezone ="Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp lastModified;

    private int lastModifiedBy;

    private List<ServiceUserDto> serviceUsers;


    public List<ServiceUserDto> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(List<ServiceUserDto> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }


    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
    
    public Integer getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(Integer primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<String> getEan() {
        return ean;
    }

    public void setEan(List<String> ean) {
        this.ean = ean;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephonenumber) {
        this.telephoneNumber = telephonenumber;
    }

    public String getFaxCode() {
        return faxCode;
    }

    public void setFaxCode(String faxCode) {
        this.faxCode = faxCode;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCvrNumber() {
        return cvrNumber;
    }

    public void setCvrNumber(String cvrNumber) {
        this.cvrNumber = cvrNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateRegion() {
        return stateRegion;
    }

    public void setStateRegion(String stateRegion) {
        this.stateRegion = stateRegion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

}
