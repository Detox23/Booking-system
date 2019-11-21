package API.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Assignment", schema = "dbo")
public class AssignmentEntity {
    private int id;
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
    private Integer createdBy;
    private Timestamp createdDate;
    private Timestamp lastModified;
    private Integer lastModifiedBy;
    private boolean isDeleted;
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
    @Column(name = "AssignmentTypeID", nullable = true)
    public Integer getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(Integer assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    @Basic
    @Column(name = "ImportanceID", nullable = true)
    public Integer getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Integer importanceId) {
        this.importanceId = importanceId;
    }

    @Basic
    @Column(name = "InterpretationTypeID", nullable = true)
    public Integer getInterpretationTypeId() {
        return interpretationTypeId;
    }

    public void setInterpretationTypeId(Integer interpretationTypeId) {
        this.interpretationTypeId = interpretationTypeId;
    }

    @Basic
    @Column(name = "AssignmentTitle", nullable = true, length = 500)
    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    @Basic
    @Column(name = "AssignmentDescription", nullable = true, length = 2147483647)
    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    @Basic
    @Column(name = "OriginStreet", nullable = true, length = 250)
    public String getOriginStreet() {
        return originStreet;
    }

    public void setOriginStreet(String originStreet) {
        this.originStreet = originStreet;
    }

    @Basic
    @Column(name = "OriginCity", nullable = true, length = 50)
    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    @Basic
    @Column(name = "OriginPostCode", nullable = true, length = 50)
    public String getOriginPostCode() {
        return originPostCode;
    }

    public void setOriginPostCode(String originPostCode) {
        this.originPostCode = originPostCode;
    }

    @Basic
    @Column(name = "OriginStateRegion", nullable = true, length = 50)
    public String getOriginStateRegion() {
        return originStateRegion;
    }

    public void setOriginStateRegion(String originStateRegion) {
        this.originStateRegion = originStateRegion;
    }

    @Basic
    @Column(name = "OriginCountry", nullable = true, length = 50)
    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Basic
    @Column(name = "DestinationStreet", nullable = true, length = 250)
    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    @Basic
    @Column(name = "DestinationCity", nullable = true, length = 50)
    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    @Basic
    @Column(name = "DestinationPostCode", nullable = true, length = 50)
    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    @Basic
    @Column(name = "DestinationStateRegion", nullable = true, length = 50)
    public String getDestinationStateRegion() {
        return destinationStateRegion;
    }

    public void setDestinationStateRegion(String destinationStateRegion) {
        this.destinationStateRegion = destinationStateRegion;
    }

    @Basic
    @Column(name = "DestinationCountry", nullable = true, length = 50)
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    @Basic
    @Column(name = "ServiceUserID", nullable = false)
    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    @Basic
    @Column(name = "AssignmentDate", nullable = true)
    public Timestamp getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Timestamp assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    @Basic
    @Column(name = "AssignmentEndDate", nullable = true)
    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
    }

    @Basic
    @Column(name = "StartTime", nullable = false)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "EndTime", nullable = false)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "TotalTime", nullable = false)
    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @CreatedBy
    @Column(name = "CreatedBy", nullable = true)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @UpdateTimestamp
    @Column(name = "LastModified", nullable = true)
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Basic
    @LastModifiedBy
    @Column(name = "LastModifiedBy", nullable = true)
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false, columnDefinition = "boolean default false")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "AssignedStatus", nullable = false)
    public boolean isAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(boolean assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    @Basic
    @Column(name = "AssignmentStatusID", nullable = true)
    public Integer getAssignmentStatusId() {
        return assignmentStatusId;
    }

    public void setAssignmentStatusId(Integer assignmentStatusId) {
        this.assignmentStatusId = assignmentStatusId;
    }

    @Basic
    @Column(name = "BookingNumber", nullable = true, length = 6)
    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @Basic
    @Column(name = "ServiceUserAccountID", nullable = true)
    public Integer getServiceUserAccountId() {
        return serviceUserAccountId;
    }

    public void setServiceUserAccountId(Integer serviceUserAccountId) {
        this.serviceUserAccountId = serviceUserAccountId;
    }

    @Basic
    @Column(name = "ServiceUserAccountEAN", nullable = true, length = 13)
    public String getServiceUserAccountEan() {
        return serviceUserAccountEan;
    }

    public void setServiceUserAccountEan(String serviceUserAccountEan) {
        this.serviceUserAccountEan = serviceUserAccountEan;
    }

    @Basic
    @Column(name = "RecurrenceType", nullable = true)
    public Integer getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(Integer recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    @Basic
    @Column(name = "RecurrencyFrequency", nullable = true)
    public Integer getRecurrencyFrequency() {
        return recurrencyFrequency;
    }

    public void setRecurrencyFrequency(Integer recurrencyFrequency) {
        this.recurrencyFrequency = recurrencyFrequency;
    }

    @Basic
    @Column(name = "OtherContactEmail", nullable = true, length = 2147483647)
    public String getOtherContactEmail() {
        return otherContactEmail;
    }

    public void setOtherContactEmail(String otherContactEmail) {
        this.otherContactEmail = otherContactEmail;
    }

    @Basic
    @Column(name = "DNTMID", nullable = true, length = 50)
    public String getDntmid() {
        return dntmid;
    }

    public void setDntmid(String dntmid) {
        this.dntmid = dntmid;
    }

    @Basic
    @Column(name = "Zone", nullable = true, length = 10)
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Basic
    @Column(name = "MoreInfo", nullable = true, length = 500)
    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Basic
    @Column(name = "KTSTID", nullable = true, length = 50)
    public String getKtstid() {
        return ktstid;
    }

    public void setKtstid(String ktstid) {
        this.ktstid = ktstid;
    }

    @Basic
    @Column(name = "KTSTParentID", nullable = true, length = 50)
    public String getKtstParentId() {
        return ktstParentId;
    }

    public void setKtstParentId(String ktstParentId) {
        this.ktstParentId = ktstParentId;
    }

    @Basic
    @Column(name = "RecurrenceKey", nullable = true, length = 50)
    public String getRecurrenceKey() {
        return recurrenceKey;
    }

    public void setRecurrenceKey(String recurrenceKey) {
        this.recurrenceKey = recurrenceKey;
    }

    @Basic
    @Column(name = "AssignmentStatusTypeID", nullable = true)
    public Integer getAssignmentStatusTypeId() {
        return assignmentStatusTypeId;
    }

    public void setAssignmentStatusTypeId(Integer assignmentStatusTypeId) {
        this.assignmentStatusTypeId = assignmentStatusTypeId;
    }

    @Basic
    @Column(name = "AssignmentPlace", nullable = true, length = 500)
    public String getAssignmentPlace() {
        return assignmentPlace;
    }

    public void setAssignmentPlace(String assignmentPlace) {
        this.assignmentPlace = assignmentPlace;
    }

    @Basic
    @Column(name = "STUKComment", nullable = true, length = 500)
    public String getStukComment() {
        return stukComment;
    }

    public void setStukComment(String stukComment) {
        this.stukComment = stukComment;
    }

    @Basic
    @Column(name = "IsResale", nullable = true)
    public Boolean getResale() {
        return isResale;
    }

    public void setResale(Boolean resale) {
        isResale = resale;
    }

    @Basic
    @Column(name = "ResaleParentId", nullable = true)
    public Integer getResaleParentId() {
        return resaleParentId;
    }

    public void setResaleParentId(Integer resaleParentId) {
        this.resaleParentId = resaleParentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentEntity that = (AssignmentEntity) o;

        if (id != that.id) return false;
        if (serviceUserId != that.serviceUserId) return false;
        if (totalTime != that.totalTime) return false;
        if (isDeleted != that.isDeleted) return false;
        if (assignedStatus != that.assignedStatus) return false;
        if (assignmentTypeId != null ? !assignmentTypeId.equals(that.assignmentTypeId) : that.assignmentTypeId != null)
            return false;
        if (importanceId != null ? !importanceId.equals(that.importanceId) : that.importanceId != null) return false;
        if (interpretationTypeId != null ? !interpretationTypeId.equals(that.interpretationTypeId) : that.interpretationTypeId != null)
            return false;
        if (assignmentTitle != null ? !assignmentTitle.equals(that.assignmentTitle) : that.assignmentTitle != null)
            return false;
        if (assignmentDescription != null ? !assignmentDescription.equals(that.assignmentDescription) : that.assignmentDescription != null)
            return false;
        if (originStreet != null ? !originStreet.equals(that.originStreet) : that.originStreet != null) return false;
        if (originCity != null ? !originCity.equals(that.originCity) : that.originCity != null) return false;
        if (originPostCode != null ? !originPostCode.equals(that.originPostCode) : that.originPostCode != null)
            return false;
        if (originStateRegion != null ? !originStateRegion.equals(that.originStateRegion) : that.originStateRegion != null)
            return false;
        if (originCountry != null ? !originCountry.equals(that.originCountry) : that.originCountry != null)
            return false;
        if (destinationStreet != null ? !destinationStreet.equals(that.destinationStreet) : that.destinationStreet != null)
            return false;
        if (destinationCity != null ? !destinationCity.equals(that.destinationCity) : that.destinationCity != null)
            return false;
        if (destinationPostCode != null ? !destinationPostCode.equals(that.destinationPostCode) : that.destinationPostCode != null)
            return false;
        if (destinationStateRegion != null ? !destinationStateRegion.equals(that.destinationStateRegion) : that.destinationStateRegion != null)
            return false;
        if (destinationCountry != null ? !destinationCountry.equals(that.destinationCountry) : that.destinationCountry != null)
            return false;
        if (assignmentDate != null ? !assignmentDate.equals(that.assignmentDate) : that.assignmentDate != null)
            return false;
        if (assignmentEndDate != null ? !assignmentEndDate.equals(that.assignmentEndDate) : that.assignmentEndDate != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;
        if (assignmentStatusId != null ? !assignmentStatusId.equals(that.assignmentStatusId) : that.assignmentStatusId != null)
            return false;
        if (bookingNumber != null ? !bookingNumber.equals(that.bookingNumber) : that.bookingNumber != null)
            return false;
        if (serviceUserAccountId != null ? !serviceUserAccountId.equals(that.serviceUserAccountId) : that.serviceUserAccountId != null)
            return false;
        if (serviceUserAccountEan != null ? !serviceUserAccountEan.equals(that.serviceUserAccountEan) : that.serviceUserAccountEan != null)
            return false;
        if (recurrenceType != null ? !recurrenceType.equals(that.recurrenceType) : that.recurrenceType != null)
            return false;
        if (recurrencyFrequency != null ? !recurrencyFrequency.equals(that.recurrencyFrequency) : that.recurrencyFrequency != null)
            return false;
        if (otherContactEmail != null ? !otherContactEmail.equals(that.otherContactEmail) : that.otherContactEmail != null)
            return false;
        if (dntmid != null ? !dntmid.equals(that.dntmid) : that.dntmid != null) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;
        if (moreInfo != null ? !moreInfo.equals(that.moreInfo) : that.moreInfo != null) return false;
        if (ktstid != null ? !ktstid.equals(that.ktstid) : that.ktstid != null) return false;
        if (ktstParentId != null ? !ktstParentId.equals(that.ktstParentId) : that.ktstParentId != null) return false;
        if (recurrenceKey != null ? !recurrenceKey.equals(that.recurrenceKey) : that.recurrenceKey != null)
            return false;
        if (assignmentStatusTypeId != null ? !assignmentStatusTypeId.equals(that.assignmentStatusTypeId) : that.assignmentStatusTypeId != null)
            return false;
        if (assignmentPlace != null ? !assignmentPlace.equals(that.assignmentPlace) : that.assignmentPlace != null)
            return false;
        if (stukComment != null ? !stukComment.equals(that.stukComment) : that.stukComment != null) return false;
        if (isResale != null ? !isResale.equals(that.isResale) : that.isResale != null) return false;
        return resaleParentId != null ? resaleParentId.equals(that.resaleParentId) : that.resaleParentId == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentTypeId != null ? assignmentTypeId.hashCode() : 0);
        result = 31 * result + (importanceId != null ? importanceId.hashCode() : 0);
        result = 31 * result + (interpretationTypeId != null ? interpretationTypeId.hashCode() : 0);
        result = 31 * result + (assignmentTitle != null ? assignmentTitle.hashCode() : 0);
        result = 31 * result + (assignmentDescription != null ? assignmentDescription.hashCode() : 0);
        result = 31 * result + (originStreet != null ? originStreet.hashCode() : 0);
        result = 31 * result + (originCity != null ? originCity.hashCode() : 0);
        result = 31 * result + (originPostCode != null ? originPostCode.hashCode() : 0);
        result = 31 * result + (originStateRegion != null ? originStateRegion.hashCode() : 0);
        result = 31 * result + (originCountry != null ? originCountry.hashCode() : 0);
        result = 31 * result + (destinationStreet != null ? destinationStreet.hashCode() : 0);
        result = 31 * result + (destinationCity != null ? destinationCity.hashCode() : 0);
        result = 31 * result + (destinationPostCode != null ? destinationPostCode.hashCode() : 0);
        result = 31 * result + (destinationStateRegion != null ? destinationStateRegion.hashCode() : 0);
        result = 31 * result + (destinationCountry != null ? destinationCountry.hashCode() : 0);
        result = 31 * result + serviceUserId;
        result = 31 * result + (assignmentDate != null ? assignmentDate.hashCode() : 0);
        result = 31 * result + (assignmentEndDate != null ? assignmentEndDate.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + totalTime;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        result = 31 * result + (assignedStatus ? 1 : 0);
        result = 31 * result + (assignmentStatusId != null ? assignmentStatusId.hashCode() : 0);
        result = 31 * result + (bookingNumber != null ? bookingNumber.hashCode() : 0);
        result = 31 * result + (serviceUserAccountId != null ? serviceUserAccountId.hashCode() : 0);
        result = 31 * result + (serviceUserAccountEan != null ? serviceUserAccountEan.hashCode() : 0);
        result = 31 * result + (recurrenceType != null ? recurrenceType.hashCode() : 0);
        result = 31 * result + (recurrencyFrequency != null ? recurrencyFrequency.hashCode() : 0);
        result = 31 * result + (otherContactEmail != null ? otherContactEmail.hashCode() : 0);
        result = 31 * result + (dntmid != null ? dntmid.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (moreInfo != null ? moreInfo.hashCode() : 0);
        result = 31 * result + (ktstid != null ? ktstid.hashCode() : 0);
        result = 31 * result + (ktstParentId != null ? ktstParentId.hashCode() : 0);
        result = 31 * result + (recurrenceKey != null ? recurrenceKey.hashCode() : 0);
        result = 31 * result + (assignmentStatusTypeId != null ? assignmentStatusTypeId.hashCode() : 0);
        result = 31 * result + (assignmentPlace != null ? assignmentPlace.hashCode() : 0);
        result = 31 * result + (stukComment != null ? stukComment.hashCode() : 0);
        result = 31 * result + (isResale != null ? isResale.hashCode() : 0);
        result = 31 * result + (resaleParentId != null ? resaleParentId.hashCode() : 0);
        return result;
    }
}
