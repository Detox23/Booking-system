package Objects;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@Table(name = "ServiceProvider", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderEntity {
    private int id;
    private Integer gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String cpr;
    private Integer source;
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
    private int createdBy;
    private Timestamp createdDate;
    private Timestamp lastModified;
    private Integer lastModifiedBy;
    private boolean isDeleted;
    private Integer departmentId;
    private byte[] serviceProviderImage;
    private Double weekHours;
    private String serviceProviderInitials;
    private boolean status;
    private String externalId;

    @Id
    @Column(name = "ID")
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
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "MiddleName")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "CPR")
    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    @Basic
    @Column(name = "Source")
    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    @Basic
    @Column(name = "TelephoneCode")
    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    @Basic
    @Column(name = "TelephoneNumber")
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "MobileCode")
    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    @Basic
    @Column(name = "MobileNumber")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Basic
    @Column(name = "FaxCode")
    public String getFaxCode() {
        return faxCode;
    }

    public void setFaxCode(String faxCode) {
        this.faxCode = faxCode;
    }

    @Basic
    @Column(name = "FaxNumber")
    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Basic
    @Column(name = "Email1")
    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    @Basic
    @Column(name = "Email2")
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Basic
    @Column(name = "Skype")
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Basic
    @Column(name = "Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "StateRegion")
    public String getStateRegion() {
        return stateRegion;
    }

    public void setStateRegion(String stateRegion) {
        this.stateRegion = stateRegion;
    }

    @Basic
    @Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "CreatedBy")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CreatedDate")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "LastModified")
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Basic
    @Column(name = "LastModifiedBy")
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Basic
    @Column(name = "IsDeleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
    @Column(name = "ServiceProviderImage")
    public byte[] getServiceProviderImage() {
        return serviceProviderImage;
    }

    public void setServiceProviderImage(byte[] serviceProviderImage) {
        this.serviceProviderImage = serviceProviderImage;
    }

    @Basic
    @Column(name = "WeekHours")
    public Double getWeekHours() {
        return weekHours;
    }

    public void setWeekHours(Double weekHours) {
        this.weekHours = weekHours;
    }

    @Basic
    @Column(name = "ServiceProviderInitials")
    public String getServiceProviderInitials() {
        return serviceProviderInitials;
    }

    public void setServiceProviderInitials(String serviceProviderInitials) {
        this.serviceProviderInitials = serviceProviderInitials;
    }

    @Basic
    @Column(name = "Status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ExternalId")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderEntity that = (ServiceProviderEntity) o;

        if (id != that.id) return false;
        if (createdBy != that.createdBy) return false;
        if (isDeleted != that.isDeleted) return false;
        if (status != that.status) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (cpr != null ? !cpr.equals(that.cpr) : that.cpr != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
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
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (!Arrays.equals(serviceProviderImage, that.serviceProviderImage)) return false;
        if (weekHours != null ? !weekHours.equals(that.weekHours) : that.weekHours != null) return false;
        if (serviceProviderInitials != null ? !serviceProviderInitials.equals(that.serviceProviderInitials) : that.serviceProviderInitials != null)
            return false;
        if (externalId != null ? !externalId.equals(that.externalId) : that.externalId != null) return false;

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
        result = 31 * result + (source != null ? source.hashCode() : 0);
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
        result = 31 * result + createdBy;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(serviceProviderImage);
        result = 31 * result + (weekHours != null ? weekHours.hashCode() : 0);
        result = 31 * result + (serviceProviderInitials != null ? serviceProviderInitials.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        return result;
    }
}
