package Objects.Factory.Database_Entities;

import Objects.Factory.Person;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Account")
public class Account implements Person {

    //region Entity attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="AccountName")
    private String accountName;

    @Column(name="AccountTypeID")
    private int accountTypeID;

    @Column(name="ParentID")
    private int parentID;

    @Column(name="PrimaryContactID")
    private int primaryContactID;

    @Column(name="DepartmentID")
    private int departmentID;

    @Column(name="EAN")
    private String ean;

    @Column(name="TelephoneCode")
    private String telephoneCode;

    @Column(name="TelephoneNumber")
    private String telephonenumber;

    @Column(name="FaxCode")
    private String faxCode;

    @Column(name="FaxNumber")
    private String faxNumber;

    @Column(name="Website")
    private String website;

    @Column(name="CVRNumber")
    private String cvrNumber;

    @Column(name="Street")
    private String street;

    @Column(name="Postcode")
    private String postcode;

    @Column(name="City")
    private String city;

    @Column(name="StateRegion")
    private String stateRegion;

    @Column(name="Country")
    private String country;

    @Column(name="CreatedBy")
    private int createdBy;

    @Column(name="CreatedDate")
    private Date createdDate;

    @Column(name="LastModified")
    private Date lastModified;

    @Column(name="LastModifiedBy")
    private String lastModifiedBy;

    @Column(name="IsDeleted")
    private boolean isDeleted;

    @Column(name="Email")
    private String email;

    @Column(name="ContactName")
    private String contactName;

    @Column(name="ContactEmail")
    private String contactEmail;

    @Column(name="ContactTelephone")
    private String contactTelephone;
    //endregion

    public Account(String accountName, int accountTypeID, int parentID,
                          int primaryContactID, int departmentID, String ean,
                          String telephoneCode, String telephonenumber, String faxCode,
                          String faxNumber, String website, String cvrNumber, String street,
                          String postcode, String city, String stateRegion, String country,
                          int createdBy, Date createdDate, Date lastModified,
                          String lastModifiedBy, boolean isDeleted, String email,
                          String contactName, String contactEmail, String contactTelephone
    ) {
        this.accountName = accountName;
        this.accountTypeID = accountTypeID;
        this.parentID = parentID;
        this.primaryContactID = primaryContactID;
        this.departmentID = departmentID;
        this.ean = ean;
        this.telephoneCode = telephoneCode;
        this.telephonenumber = telephonenumber;
        this.faxCode = faxCode;
        this.faxNumber = faxNumber;
        this.website = website;
        this.cvrNumber = cvrNumber;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.stateRegion = stateRegion;
        this.country = country;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
        this.lastModifiedBy = lastModifiedBy;
        this.isDeleted = isDeleted;
        this.email = email;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactTelephone = contactTelephone;
    }

    public Account(String accountName, int accountTypeID){
        this.accountName = accountName;
        this.accountTypeID = accountTypeID;
    }

    public Account(int id){
        this.id = id;
    }

    public Account() {

    }

    //region Getters and setters
    public String getAccountName() {
        return accountName;
    }

    public int getId(){
        return this.id;
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

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getPrimaryContactID() {
        return primaryContactID;
    }

    public void setPrimaryContactID(int primaryContactID) {
        this.primaryContactID = primaryContactID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
    //endregion
    @Override
    public void print() {
        System.out.printf("%s, %d", accountName, email);
    }
}
