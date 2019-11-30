package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class AssignmentForUpdateDto {

    private Integer id;
    private String assignmentDescription;
    private String destinationStreet;
    private String destinationCity;
    private String destinationPostCode;
    private String destinationStateRegion;
    private String destinationCountry;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp assignmentDate;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date assignmentEndDate;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Timestamp startTime;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Timestamp endTime;
    private Boolean assignedStatus;
    private String bookingNumber;
    private String serviceUserAccountEan;
    private Integer recurrenceType;
    private Integer recurrencyFrequency;
    private String otherContactEmail;
    private String dntmid;
    private String zone;
    private String moreInfo;
    private String ktstid;
    private String ktstParentId;
    private String recurrenceKey;
    private String assignmentPlace;
    private String stukComment;
    private Boolean isResale;
    private Integer resaleParentId;
    private Integer assignmentTypeId;
    private Integer assignmentImportanceId;
    private Integer assignmentInterpretationTypeId;
    private String assignmentTitle;
    private Integer serviceUserId;
    private Integer accountId;
    private Integer assignmentStatusId;
    private Integer vocalLanguageId;
    private List<Integer> serviceProviders;
    private List<Integer> assignmentStatusTypeIds;
    private List<Integer> stukYearCodes;

    public List<Integer> getStukYearCodes() {
        return stukYearCodes;
    }

    public void setStukYearCodes(List<Integer> stukYearCodes) {
        this.stukYearCodes = stukYearCodes;
    }

    public Integer getVocalLanguageId() {
        return vocalLanguageId;
    }

    public void setVocalLanguageId(Integer vocalLanguageId) {
        this.vocalLanguageId = vocalLanguageId;
    }


    public List<Integer> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<Integer> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }


    public List<Integer> getAssignmentStatusTypeIds() {
        return assignmentStatusTypeIds;
    }

    public void setAssignmentStatusTypeIds(List<Integer> assignmentStatusTypeIds) {
        this.assignmentStatusTypeIds = assignmentStatusTypeIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    public String getDestinationStateRegion() {
        return destinationStateRegion;
    }

    public void setDestinationStateRegion(String destinationStateRegion) {
        this.destinationStateRegion = destinationStateRegion;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Timestamp getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Timestamp assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Boolean getAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(Boolean assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getServiceUserAccountEan() {
        return serviceUserAccountEan;
    }

    public void setServiceUserAccountEan(String serviceUserAccountEan) {
        this.serviceUserAccountEan = serviceUserAccountEan;
    }

    public Integer getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(Integer recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public Integer getRecurrencyFrequency() {
        return recurrencyFrequency;
    }

    public void setRecurrencyFrequency(Integer recurrencyFrequency) {
        this.recurrencyFrequency = recurrencyFrequency;
    }

    public String getOtherContactEmail() {
        return otherContactEmail;
    }

    public void setOtherContactEmail(String otherContactEmail) {
        this.otherContactEmail = otherContactEmail;
    }

    public String getDntmid() {
        return dntmid;
    }

    public void setDntmid(String dntmid) {
        this.dntmid = dntmid;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getKtstid() {
        return ktstid;
    }

    public void setKtstid(String ktstid) {
        this.ktstid = ktstid;
    }

    public String getKtstParentId() {
        return ktstParentId;
    }

    public void setKtstParentId(String ktstParentId) {
        this.ktstParentId = ktstParentId;
    }

    public String getRecurrenceKey() {
        return recurrenceKey;
    }

    public void setRecurrenceKey(String recurrenceKey) {
        this.recurrenceKey = recurrenceKey;
    }

    public String getAssignmentPlace() {
        return assignmentPlace;
    }

    public void setAssignmentPlace(String assignmentPlace) {
        this.assignmentPlace = assignmentPlace;
    }

    public String getStukComment() {
        return stukComment;
    }

    public void setStukComment(String stukComment) {
        this.stukComment = stukComment;
    }

    public Boolean getResale() {
        return isResale;
    }

    public void setResale(Boolean resale) {
        isResale = resale;
    }

    public Integer getResaleParentId() {
        return resaleParentId;
    }

    public void setResaleParentId(Integer resaleParentId) {
        this.resaleParentId = resaleParentId;
    }

    public Integer getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(Integer assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    public Integer getAssignmentImportanceId() {
        return assignmentImportanceId;
    }

    public void setAssignmentImportanceId(Integer assignmentImportanceId) {
        this.assignmentImportanceId = assignmentImportanceId;
    }

    public Integer getAssignmentInterpretationTypeId() {
        return assignmentInterpretationTypeId;
    }

    public void setAssignmentInterpretationTypeId(Integer assignmentInterpretationTypeId) {
        this.assignmentInterpretationTypeId = assignmentInterpretationTypeId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public Integer getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(Integer serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAssignmentStatusId() {
        return assignmentStatusId;
    }

    public void setAssignmentStatusId(Integer assignmentStatusId) {
        this.assignmentStatusId = assignmentStatusId;
    }
}