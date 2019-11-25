package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "ServiceProvider", schema = "dbo")
public class ServiceProviderEntity {
    private Integer id;
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
    private Timestamp lastModified;
    private Integer lastModifiedBy;
    private Boolean isDeleted;
    private Integer departmentId;
    private byte[] serviceProviderImage;
    private Double weekHours;
    private String serviceProviderInitials;
    private Boolean status;
    private String externalId;
    private ServiceProviderSourceEntity serviceProviderSourceBySource;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationByPreferredNotificationId;
    private TransportTypeEntity transportTypeByTransportId;
    private Collection<ServiceProviderAbsenceEntity> serviceProviderAbsencesById;
    private Collection<ServiceProviderCommentEntity> serviceProviderCommentsById;
    private Collection<ServiceProviderEveningWorkEntity> serviceProviderEveningWorksById;
    private Collection<ServiceProviderServiceProviderCompetencyEntity> serviceProviderServiceProviderCompetenciesById;
    private Collection<ServiceProviderServiceProviderTypeEntity> serviceProviderServiceProviderTypesById;
    private Collection<ServiceUserPreferencesEntity> serviceUserPreferencesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Gender", nullable = true)
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "FirstName", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "MiddleName", nullable = true, length = 50)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "LastName", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "CPR", nullable = true, length = 250)
    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    @Basic
    @Column(name = "TelephoneCode", nullable = true, length = 50)
    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    @Basic
    @Column(name = "TelephoneNumber", nullable = true, length = 50)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "MobileCode", nullable = true, length = 50)
    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    @Basic
    @Column(name = "MobileNumber", nullable = true, length = 50)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Basic
    @Column(name = "FaxCode", nullable = true, length = 50)
    public String getFaxCode() {
        return faxCode;
    }

    public void setFaxCode(String faxCode) {
        this.faxCode = faxCode;
    }

    @Basic
    @Column(name = "FaxNumber", nullable = true, length = 50)
    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Basic
    @Column(name = "Email1", nullable = true, length = 50)
    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    @Basic
    @Column(name = "Email2", nullable = true, length = 50)
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Basic
    @Column(name = "Skype", nullable = true, length = 50)
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Basic
    @Column(name = "Street", nullable = true, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Postcode", nullable = true, length = 50)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "City", nullable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "StateRegion", nullable = true, length = 50)
    public String getStateRegion() {
        return stateRegion;
    }

    public void setStateRegion(String stateRegion) {
        this.stateRegion = stateRegion;
    }

    @Basic
    @Column(name = "Country", nullable = true, length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = false)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "LastModified", nullable = true)
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Basic
    @Column(name = "LastModifiedBy", nullable = true)
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "DepartmentID", nullable = true)
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "ServiceProviderImage", nullable = true)
    public byte[] getServiceProviderImage() {
        return serviceProviderImage;
    }

    public void setServiceProviderImage(byte[] serviceProviderImage) {
        this.serviceProviderImage = serviceProviderImage;
    }

    @Basic
    @Column(name = "WeekHours", nullable = true, precision = 0)
    public Double getWeekHours() {
        return weekHours;
    }

    public void setWeekHours(Double weekHours) {
        this.weekHours = weekHours;
    }

    @Basic
    @Column(name = "ServiceProviderInitials", nullable = true, length = 70)
    public String getServiceProviderInitials() {
        return serviceProviderInitials;
    }

    public void setServiceProviderInitials(String serviceProviderInitials) {
        this.serviceProviderInitials = serviceProviderInitials;
    }

    @Basic
    @Column(name = "Status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ExternalId", nullable = true, length = 50)
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
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
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (!Arrays.equals(serviceProviderImage, that.serviceProviderImage)) return false;
        if (weekHours != null ? !weekHours.equals(that.weekHours) : that.weekHours != null) return false;
        if (serviceProviderInitials != null ? !serviceProviderInitials.equals(that.serviceProviderInitials) : that.serviceProviderInitials != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (externalId != null ? !externalId.equals(that.externalId) : that.externalId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(serviceProviderImage);
        result = 31 * result + (weekHours != null ? weekHours.hashCode() : 0);
        result = 31 * result + (serviceProviderInitials != null ? serviceProviderInitials.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Source", referencedColumnName = "ID")
    public ServiceProviderSourceEntity getServiceProviderSourceBySource() {
        return serviceProviderSourceBySource;
    }

    public void setServiceProviderSourceBySource(ServiceProviderSourceEntity serviceProviderSourceBySource) {
        this.serviceProviderSourceBySource = serviceProviderSourceBySource;
    }

    @ManyToOne
    @JoinColumn(name = "PreferredNotificationID", referencedColumnName = "ID")
    public ServiceProviderPreferredNotificationEntity getServiceProviderPreferredNotificationByPreferredNotificationId() {
        return serviceProviderPreferredNotificationByPreferredNotificationId;
    }

    public void setServiceProviderPreferredNotificationByPreferredNotificationId(ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationByPreferredNotificationId) {
        this.serviceProviderPreferredNotificationByPreferredNotificationId = serviceProviderPreferredNotificationByPreferredNotificationId;
    }

    @ManyToOne
    @JoinColumn(name = "TransportID", referencedColumnName = "ID")
    public TransportTypeEntity getTransportTypeByTransportId() {
        return transportTypeByTransportId;
    }

    public void setTransportTypeByTransportId(TransportTypeEntity transportTypeByTransportId) {
        this.transportTypeByTransportId = transportTypeByTransportId;
    }

    @OneToMany(mappedBy = "serviceProviderByServiceProviderId")
    public Collection<ServiceProviderAbsenceEntity> getServiceProviderAbsencesById() {
        return serviceProviderAbsencesById;
    }

    public void setServiceProviderAbsencesById(Collection<ServiceProviderAbsenceEntity> serviceProviderAbsencesById) {
        this.serviceProviderAbsencesById = serviceProviderAbsencesById;
    }

    @OneToMany(mappedBy = "serviceProviderByServiceProviderId")
    public Collection<ServiceProviderCommentEntity> getServiceProviderCommentsById() {
        return serviceProviderCommentsById;
    }

    public void setServiceProviderCommentsById(Collection<ServiceProviderCommentEntity> serviceProviderCommentsById) {
        this.serviceProviderCommentsById = serviceProviderCommentsById;
    }

    @OneToMany(mappedBy = "serviceProviderByServiceProviderId")
    public Collection<ServiceProviderEveningWorkEntity> getServiceProviderEveningWorksById() {
        return serviceProviderEveningWorksById;
    }

    public void setServiceProviderEveningWorksById(Collection<ServiceProviderEveningWorkEntity> serviceProviderEveningWorksById) {
        this.serviceProviderEveningWorksById = serviceProviderEveningWorksById;
    }

    @OneToMany(mappedBy = "serviceProviderByServiceProviderId")
    public Collection<ServiceProviderServiceProviderCompetencyEntity> getServiceProviderServiceProviderCompetenciesById() {
        return serviceProviderServiceProviderCompetenciesById;
    }

    public void setServiceProviderServiceProviderCompetenciesById(Collection<ServiceProviderServiceProviderCompetencyEntity> serviceProviderServiceProviderCompetenciesById) {
        this.serviceProviderServiceProviderCompetenciesById = serviceProviderServiceProviderCompetenciesById;
    }

    @OneToMany(mappedBy = "serviceProviderByServiceProviderId")
    public Collection<ServiceProviderServiceProviderTypeEntity> getServiceProviderServiceProviderTypesById() {
        return serviceProviderServiceProviderTypesById;
    }

    public void setServiceProviderServiceProviderTypesById(Collection<ServiceProviderServiceProviderTypeEntity> serviceProviderServiceProviderTypesById) {
        this.serviceProviderServiceProviderTypesById = serviceProviderServiceProviderTypesById;
    }

    @OneToMany(mappedBy = "serviceProviderByServiceProviderId")
    public Collection<ServiceUserPreferencesEntity> getServiceUserPreferencesById() {
        return serviceUserPreferencesById;
    }

    public void setServiceUserPreferencesById(Collection<ServiceUserPreferencesEntity> serviceUserPreferencesById) {
        this.serviceUserPreferencesById = serviceUserPreferencesById;
    }
}
