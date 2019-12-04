package API.Models.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ServiceUser", schema = "dbo")
public class ServiceUserEntity {
    private int id;
    private Integer gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String cpr;
    private String telephoneCode;
    private String telephoneNumber;
    private String mobileCode;
    private String mobileNumber;
    private String faxCode;
    private String faxNumber;
    private String email1;
    private String email2;
    private String skype;
    private String street;
    private String postcode;
    private String city;
    private String stateRegion;
    private String country;
    private Integer createdBy;
    private Timestamp createdDate;
    private Integer lastModifiedBy;
    private Timestamp lastModified;
    private boolean isDeleted;
    private byte[] serviceUserImage;
    private Integer departmentId;
    private int statusId;
    private String externalId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Gender")
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "FirstName", length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "MiddleName", length = 50)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "LastName", length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "CPR", length = 250)
    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
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
    @Column(name = "MobileCode", length = 50)
    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    @Basic
    @Column(name = "MobileNumber", length = 50)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
    @Column(name = "Email1", length = 2147483647)
    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    @Basic
    @Column(name = "Email2", length = 50)
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Basic
    @Column(name = "Skype", length = 50)
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
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
    @Column(name = "CreatedBy")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
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
    @LastModifiedBy
    @Column(name = "LastModifiedBy")
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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
    @Column(name = "IsDeleted", nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "ServiceUserImage")
    public byte[] getServiceUserImage() {
        return serviceUserImage;
    }

    public void setServiceUserImage(byte[] serviceUserImage) {
        this.serviceUserImage = serviceUserImage;
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
    @Column(name = "StatusID", nullable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "ExternalId", length = 50)
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceUserEntity that = (ServiceUserEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (statusId != that.statusId) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (cpr != null ? !cpr.equals(that.cpr) : that.cpr != null) return false;
        if (telephoneCode != null ? !telephoneCode.equals(that.telephoneCode) : that.telephoneCode != null)
            return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(that.telephoneNumber) : that.telephoneNumber != null)
            return false;
        if (mobileCode != null ? !mobileCode.equals(that.mobileCode) : that.mobileCode != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(that.mobileNumber) : that.mobileNumber != null) return false;
        if (faxCode != null ? !faxCode.equals(that.faxCode) : that.faxCode != null) return false;
        if (faxNumber != null ? !faxNumber.equals(that.faxNumber) : that.faxNumber != null) return false;
        if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (skype != null ? !skype.equals(that.skype) : that.skype != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (stateRegion != null ? !stateRegion.equals(that.stateRegion) : that.stateRegion != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (!Arrays.equals(serviceUserImage, that.serviceUserImage)) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (cpr != null ? cpr.hashCode() : 0);
        result = 31 * result + (telephoneCode != null ? telephoneCode.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (mobileCode != null ? mobileCode.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (faxCode != null ? faxCode.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (stateRegion != null ? stateRegion.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        result = 31 * result + Arrays.hashCode(serviceUserImage);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        return result;
    }
}
