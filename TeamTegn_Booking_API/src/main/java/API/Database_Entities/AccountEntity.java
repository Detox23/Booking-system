package API.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicUpdate
@Table(name = "Account", schema = "dbo")
public class AccountEntity {

    private int id; //skipped
    private String accountName;
    private AccountTypeEntity accountTypeByAccountTypeId; //accountTypeID
    private Integer parentId;
    private Integer primaryContactId;
    private Integer departmentId;
    private String ean; //as string [database limitations]
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
    private Timestamp createdDate; //done automatically
    private Timestamp lastModified; // skipped
    private Integer lastModifiedBy; // skipped
    private boolean deleted; //done automatically
    private String email;
    private String contactName;
    private String contactEmail;
    private String contactTelephone;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AccountName", nullable = false)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Basic
    @Column(name = "ParentID", nullable = false)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "PrimaryContactID")
    public Integer getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(Integer primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    @Basic
    @Column(name = "DepartmentID")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "EAN", length = 50, columnDefinition = "string default null")
    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    @Basic
    @Column(name = "TelephoneCode", length = 50)
    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    @Basic
    @Column(name = "TelephoneNumber", length = 50)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "FaxCode", length = 50)
    public String getFaxCode() {
        return faxCode;
    }

    public void setFaxCode(String faxCode) {
        this.faxCode = faxCode;
    }

    @Basic
    @Column(name = "FaxNumber", length = 50)
    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Basic
    @Column(name = "Website", length = 100)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "CVRNumber", length = 50)
    public String getCvrNumber() {
        return cvrNumber;
    }

    public void setCvrNumber(String cvrNumber) {
        this.cvrNumber = cvrNumber;
    }

    @Basic
    @Column(name = "Street", length = 150)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Postcode", length = 50)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "City", length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "StateRegion", length = 50)
    public String getStateRegion() {
        return stateRegion;
    }

    public void setStateRegion(String stateRegion) {
        this.stateRegion = stateRegion;
    }

    @Basic
    @Column(name = "Country", length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @CreatedBy
    @Column(name = "CreatedBy", nullable = false)
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "CreatedDate")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @UpdateTimestamp
    @Column(name = "LastModified")
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Basic
    @LastModifiedBy
    @Column(name = "LastModifiedBy")
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Basic
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "IsDeleted", nullable = false, columnDefinition = "boolean default false")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "Email", length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ContactName", length = 100)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "ContactEmail", length = 100)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Basic
    @Column(name = "ContactTelephone", length = 100)
    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }


    @SuppressWarnings("EqualsReplaceableByObjectsCall")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (id != that.id) return false;
        if (!parentId.equals(that.parentId)) return false;
        if (createdBy != that.createdBy) return false;
        if (deleted != that.deleted) return false;
        if (accountName != null ? !accountName.equals(that.accountName) : that.accountName != null) return false;
        if (primaryContactId != null ? !primaryContactId.equals(that.primaryContactId) : that.primaryContactId != null)
            return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (ean != null ? !ean.equals(that.ean) : that.ean != null) return false;
        if (telephoneCode != null ? !telephoneCode.equals(that.telephoneCode) : that.telephoneCode != null)
            return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(that.telephoneNumber) : that.telephoneNumber != null)
            return false;
        if (faxCode != null ? !faxCode.equals(that.faxCode) : that.faxCode != null) return false;
        if (faxNumber != null ? !faxNumber.equals(that.faxNumber) : that.faxNumber != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (cvrNumber != null ? !cvrNumber.equals(that.cvrNumber) : that.cvrNumber != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (stateRegion != null ? !stateRegion.equals(that.stateRegion) : that.stateRegion != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
        if (contactEmail != null ? !contactEmail.equals(that.contactEmail) : that.contactEmail != null) return false;
        return contactTelephone != null ? contactTelephone.equals(that.contactTelephone) : that.contactTelephone == null;
    }


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "AccountTypeID", referencedColumnName = "ID", nullable = false)
    public AccountTypeEntity getAccountTypeByAccountTypeId() {
        return accountTypeByAccountTypeId;
    }

    public void setAccountTypeByAccountTypeId(AccountTypeEntity accountTypeByAccountTypeId) {
        this.accountTypeByAccountTypeId = accountTypeByAccountTypeId;
    }
}
