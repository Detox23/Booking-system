package Shared.ForCreation;

import Shared.ToReturn.AccountTypeDto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class AccountForUpdateDto {

    @NotNull
    private int id;

    @NotNull
    private String accountName;

    @NotNull
    private Integer accountTypeId;

    @NotNull
    private Integer parentId;

    @NotNull
    private Integer primaryContactId;

    @NotNull
    private Integer departmentId;

    private List<String> ean;

    private String telephoneCode;

    private String telephoneNumber;

    private String faxCode;

    private String faxNumber;

    private String website;

    @NotNull
    private String cvrNumber;

    private String street;

    private String postcode;

    private String city;

    private String stateRegion;

    private String country;

    private String email;

    private String contactName;

    private String contactEmail;

    private String contactTelephone;

    private AccountTypeDto accountType;

    private List<Integer> accountComments;

    private Timestamp lastModified;

    private int lastModifiedBy;

    private List<Integer> serviceUsers;


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

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public AccountTypeDto getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDto accountType) {
        this.accountType = accountType;
    }

    public List<Integer> getAccountComments() {
        return accountComments;
    }

    public void setAccountComments(List<Integer> accountComments) {
        this.accountComments = accountComments;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public List<Integer> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(List<Integer> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }
}
