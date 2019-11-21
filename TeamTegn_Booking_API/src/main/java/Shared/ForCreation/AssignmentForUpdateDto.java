package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class AssignmentForUpdateDto {
    private Integer assignmentTypeId;
    private Integer importanceId;
    private Integer interpretationTypeId;
    private String assignmentTitle;
    private String assignmentDescription;
    private String originStreet;
    private String originCity;
    private String originPostCode;
    private String originStateRegion;
    private String originCountry;
    private String destinationStreet;
    private String destinationCity;
    private String destinationPostCode;
    private String destinationStateRegion;
    private String destinationCountry;
    private int serviceUserId;
    private Timestamp assignmentDate;
    private Date assignmentEndDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private int totalTime;
    private boolean assignedStatus;
    private Integer assignmentStatusId;
    private String bookingNumber;
    private Integer serviceUserAccountId;
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
    private Integer assignmentStatusTypeId;
    private String assignmentPlace;
    private String stukComment;
    private Boolean isResale;
    private Integer resaleParentId;
    private List<Integer> assignmentStatusTypeIds;
    private List<Integer> serviceProviders;


    public List<Integer> getAssignmentStatusTypeIds() {
        return assignmentStatusTypeIds;
    }

    public void setAssignmentStatusTypeIds(List<Integer> assignmentStatusTypeIds) {
        this.assignmentStatusTypeIds = assignmentStatusTypeIds;
    }

    public Integer getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(Integer assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    public Integer getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Integer importanceId) {
        this.importanceId = importanceId;
    }

    public Integer getInterpretationTypeId() {
        return interpretationTypeId;
    }

    public void setInterpretationTypeId(Integer interpretationTypeId) {
        this.interpretationTypeId = interpretationTypeId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getOriginStreet() {
        return originStreet;
    }

    public void setOriginStreet(String originStreet) {
        this.originStreet = originStreet;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginPostCode() {
        return originPostCode;
    }

    public void setOriginPostCode(String originPostCode) {
        this.originPostCode = originPostCode;
    }

    public String getOriginStateRegion() {
        return originStateRegion;
    }

    public void setOriginStateRegion(String originStateRegion) {
        this.originStateRegion = originStateRegion;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
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

    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Berlin")

    public Timestamp getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Timestamp assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Berlin")

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Berlin")

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(boolean assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public Integer getAssignmentStatusId() {
        return assignmentStatusId;
    }

    public void setAssignmentStatusId(Integer assignmentStatusId) {
        this.assignmentStatusId = assignmentStatusId;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Integer getServiceUserAccountId() {
        return serviceUserAccountId;
    }

    public void setServiceUserAccountId(Integer serviceUserAccountId) {
        this.serviceUserAccountId = serviceUserAccountId;
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

    public Integer getAssignmentStatusTypeId() {
        return assignmentStatusTypeId;
    }

    public void setAssignmentStatusTypeId(Integer assignmentStatusTypeId) {
        this.assignmentStatusTypeId = assignmentStatusTypeId;
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
}