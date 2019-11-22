package Shared.ForCreation;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ServiceProviderForCreationDto {
    private Integer gender;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    private String cpr;
    @NotNull
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
    @NotNull
    private Integer departmentId;
    private byte[] serviceProviderImage;
    private Double weekHours;
    private String serviceProviderInitials;
    private String externalId;
    private boolean status;
    private List<Integer> competencies;

    public List<Integer> getCompetencies(){
        return competencies;
    }

    public void setCompetencies(List<Integer> competencies){
        this.competencies = competencies;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public byte[] getServiceProviderImage() {
        return serviceProviderImage;
    }

    public void setServiceProviderImage(byte[] serviceProviderImage) {
        this.serviceProviderImage = serviceProviderImage;
    }

    public Double getWeekHours() {
        return weekHours;
    }

    public void setWeekHours(Double weekHours) {
        this.weekHours = weekHours;
    }

    public String getServiceProviderInitials() {
        return serviceProviderInitials;
    }

    public void setServiceProviderInitials(String serviceProviderInitials) {
        this.serviceProviderInitials = serviceProviderInitials;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }


}
