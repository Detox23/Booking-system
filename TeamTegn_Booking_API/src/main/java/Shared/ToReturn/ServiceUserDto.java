package Shared.ToReturn;

import API.Database_Entities.*;

import java.sql.Timestamp;
import java.util.Collection;

public class ServiceUserDto {
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
    private Integer lastModifiedBy;
    private Timestamp lastModified;
    private Boolean isDeleted;
    private byte[] serviceUserImage;
    private String username;
    private String password;
    private String externalId;
    private Integer roleId;
    private Collection<AssignmentDto> assignmentsById;
    private DepartmentDto departmentByDepartmentId;
    private ServiceUserStatusDto serviceUserStatusByStatusId;
    private Collection<ServiceUserPreferencesDto> serviceUserPreferencesById;
    private Collection<ServiceUserAccountDto> serviceUserAccountsById;
    private Collection<ServiceUserCommentDto> serviceUserCommentsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
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

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public byte[] getServiceUserImage() {
        return serviceUserImage;
    }

    public void setServiceUserImage(byte[] serviceUserImage) {
        this.serviceUserImage = serviceUserImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Collection<AssignmentDto> getAssignmentsById() {
        return assignmentsById;
    }

    public void setAssignmentsById(Collection<AssignmentDto> assignmentsById) {
        this.assignmentsById = assignmentsById;
    }

    public DepartmentDto getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(DepartmentDto departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }

    public ServiceUserStatusDto getServiceUserStatusByStatusId() {
        return serviceUserStatusByStatusId;
    }

    public void setServiceUserStatusByStatusId(ServiceUserStatusDto serviceUserStatusByStatusId) {
        this.serviceUserStatusByStatusId = serviceUserStatusByStatusId;
    }

    public Collection<ServiceUserPreferencesDto> getServiceUserPreferencesById() {
        return serviceUserPreferencesById;
    }

    public void setServiceUserPreferencesById(Collection<ServiceUserPreferencesDto> serviceUserPreferencesById) {
        this.serviceUserPreferencesById = serviceUserPreferencesById;
    }

    public Collection<ServiceUserAccountDto> getServiceUserAccountsById() {
        return serviceUserAccountsById;
    }

    public void setServiceUserAccountsById(Collection<ServiceUserAccountDto> serviceUserAccountsById) {
        this.serviceUserAccountsById = serviceUserAccountsById;
    }

    public Collection<ServiceUserCommentDto> getServiceUserCommentsById() {
        return serviceUserCommentsById;
    }

    public void setServiceUserCommentsById(Collection<ServiceUserCommentDto> serviceUserCommentsById) {
        this.serviceUserCommentsById = serviceUserCommentsById;
    }
}
