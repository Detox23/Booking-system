package API.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "Assignment", schema = "dbo")
public class AssignmentEntity {
    private Integer id;
    private String assignmentDescription;
    private String destinationStreet;
    private String destinationCity;
    private String destinationPostCode;
    private String destinationStateRegion;
    private String destinationCountry;
    private Timestamp assignmentDate;
    private Date assignmentEndDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer totalTime;
    @CreatedBy
    private Integer createdBy;
    @CreationTimestamp
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp lastModified;
    @LastModifiedBy
    private Integer lastModifiedBy;
    private Boolean isDeleted;
    private Boolean assignedStatus;
    private Integer assignmentStatusId;
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
    private AssignmentTypeEntity assignmentTypeByAssignmentTypeId;
    private AssignmentImportanceEntity assignmentImportanceByImportanceId;
    private AssignmentInterpretationTypeEntity assignmentInterpretationTypeByInterpretationTypeId;
    private AssignmentTitleEntity assignmentTitleByAssignmentTitle;
    private ServiceUserEntity serviceUserByServiceUserId;
    private AccountEntity accountByServiceUserAccountId;
    private AssignmentStatusEntity assignmentStatusByAssignmentStatusTypeId;
    private VocalLanguagesEntity vocalLanguagesByVocalLanguageId;
    private Collection<AssignmentAssignmentStatusTypeEntity> assignmentAssignmentStatusTypesById;
    private Collection<AssignmentCommentEntity> assignmentCommentsById;
    private Collection<Assignment_StukYearCodeEntity> assignmentStukYearCodesById;
    private Collection<AssignmentServiceProviderEntity> assignmentServiceProvidersById;
    private Collection<PdfSignatureLinkEntity> pdfSignatureLinksById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = true)
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
    @Column(name = "AssignedStatus", nullable = false)
    public Boolean getAssignedStatus() {
        return assignedStatus;
    }

    public void setAssignedStatus(Boolean assignedStatus) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (assignmentDescription != null ? !assignmentDescription.equals(that.assignmentDescription) : that.assignmentDescription != null)
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
        if (totalTime != null ? !totalTime.equals(that.totalTime) : that.totalTime != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (assignedStatus != null ? !assignedStatus.equals(that.assignedStatus) : that.assignedStatus != null)
            return false;
        if (assignmentStatusId != null ? !assignmentStatusId.equals(that.assignmentStatusId) : that.assignmentStatusId != null)
            return false;
        if (bookingNumber != null ? !bookingNumber.equals(that.bookingNumber) : that.bookingNumber != null)
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
        if (assignmentPlace != null ? !assignmentPlace.equals(that.assignmentPlace) : that.assignmentPlace != null)
            return false;
        if (stukComment != null ? !stukComment.equals(that.stukComment) : that.stukComment != null) return false;
        if (isResale != null ? !isResale.equals(that.isResale) : that.isResale != null) return false;
        if (resaleParentId != null ? !resaleParentId.equals(that.resaleParentId) : that.resaleParentId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assignmentDescription != null ? assignmentDescription.hashCode() : 0);
        result = 31 * result + (destinationStreet != null ? destinationStreet.hashCode() : 0);
        result = 31 * result + (destinationCity != null ? destinationCity.hashCode() : 0);
        result = 31 * result + (destinationPostCode != null ? destinationPostCode.hashCode() : 0);
        result = 31 * result + (destinationStateRegion != null ? destinationStateRegion.hashCode() : 0);
        result = 31 * result + (destinationCountry != null ? destinationCountry.hashCode() : 0);
        result = 31 * result + (assignmentDate != null ? assignmentDate.hashCode() : 0);
        result = 31 * result + (assignmentEndDate != null ? assignmentEndDate.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (totalTime != null ? totalTime.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (assignedStatus != null ? assignedStatus.hashCode() : 0);
        result = 31 * result + (assignmentStatusId != null ? assignmentStatusId.hashCode() : 0);
        result = 31 * result + (bookingNumber != null ? bookingNumber.hashCode() : 0);
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
        result = 31 * result + (assignmentPlace != null ? assignmentPlace.hashCode() : 0);
        result = 31 * result + (stukComment != null ? stukComment.hashCode() : 0);
        result = 31 * result + (isResale != null ? isResale.hashCode() : 0);
        result = 31 * result + (resaleParentId != null ? resaleParentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentTypeID", referencedColumnName = "ID")
    public AssignmentTypeEntity getAssignmentTypeByAssignmentTypeId() {
        return assignmentTypeByAssignmentTypeId;
    }

    public void setAssignmentTypeByAssignmentTypeId(AssignmentTypeEntity assignmentTypeByAssignmentTypeId) {
        this.assignmentTypeByAssignmentTypeId = assignmentTypeByAssignmentTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "ImportanceID", referencedColumnName = "ID")
    public AssignmentImportanceEntity getAssignmentImportanceByImportanceId() {
        return assignmentImportanceByImportanceId;
    }

    public void setAssignmentImportanceByImportanceId(AssignmentImportanceEntity assignmentImportanceByImportanceId) {
        this.assignmentImportanceByImportanceId = assignmentImportanceByImportanceId;
    }

    @ManyToOne
    @JoinColumn(name = "InterpretationTypeID", referencedColumnName = "ID")
    public AssignmentInterpretationTypeEntity getAssignmentInterpretationTypeByInterpretationTypeId() {
        return assignmentInterpretationTypeByInterpretationTypeId;
    }

    public void setAssignmentInterpretationTypeByInterpretationTypeId(AssignmentInterpretationTypeEntity assignmentInterpretationTypeByInterpretationTypeId) {
        this.assignmentInterpretationTypeByInterpretationTypeId = assignmentInterpretationTypeByInterpretationTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentTitle", referencedColumnName = "Title")
    public AssignmentTitleEntity getAssignmentTitleByAssignmentTitle() {
        return assignmentTitleByAssignmentTitle;
    }

    public void setAssignmentTitleByAssignmentTitle(AssignmentTitleEntity assignmentTitleByAssignmentTitle) {
        this.assignmentTitleByAssignmentTitle = assignmentTitleByAssignmentTitle;
    }

    @ManyToOne
    @JoinColumn(name = "ServiceUserID", referencedColumnName = "ID", nullable = false)
    public ServiceUserEntity getServiceUserByServiceUserId() {
        return serviceUserByServiceUserId;
    }

    public void setServiceUserByServiceUserId(ServiceUserEntity serviceUserByServiceUserId) {
        this.serviceUserByServiceUserId = serviceUserByServiceUserId;
    }

    @ManyToOne
    @JoinColumn(name = "ServiceUserAccountID", referencedColumnName = "ID")
    public AccountEntity getAccountByServiceUserAccountId() {
        return accountByServiceUserAccountId;
    }

    public void setAccountByServiceUserAccountId(AccountEntity accountByServiceUserAccountId) {
        this.accountByServiceUserAccountId = accountByServiceUserAccountId;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentStatusTypeID", referencedColumnName = "ID")
    public AssignmentStatusEntity getAssignmentStatusByAssignmentStatusTypeId() {
        return assignmentStatusByAssignmentStatusTypeId;
    }

    public void setAssignmentStatusByAssignmentStatusTypeId(AssignmentStatusEntity assignmentStatusByAssignmentStatusTypeId) {
        this.assignmentStatusByAssignmentStatusTypeId = assignmentStatusByAssignmentStatusTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "VocalLanguageID", referencedColumnName = "ID")
    public VocalLanguagesEntity getVocalLanguagesByVocalLanguageId() {
        return vocalLanguagesByVocalLanguageId;
    }

    public void setVocalLanguagesByVocalLanguageId(VocalLanguagesEntity vocalLanguagesByVocalLanguageId) {
        this.vocalLanguagesByVocalLanguageId = vocalLanguagesByVocalLanguageId;
    }

    @OneToMany(mappedBy = "assignmentByAssignmentId")
    public Collection<AssignmentAssignmentStatusTypeEntity> getAssignmentAssignmentStatusTypesById() {
        return assignmentAssignmentStatusTypesById;
    }

    public void setAssignmentAssignmentStatusTypesById(Collection<AssignmentAssignmentStatusTypeEntity> assignmentAssignmentStatusTypesById) {
        this.assignmentAssignmentStatusTypesById = assignmentAssignmentStatusTypesById;
    }

    @OneToMany(mappedBy = "assignmentByAssignmentId")
    public Collection<AssignmentCommentEntity> getAssignmentCommentsById() {
        return assignmentCommentsById;
    }

    public void setAssignmentCommentsById(Collection<AssignmentCommentEntity> assignmentCommentsById) {
        this.assignmentCommentsById = assignmentCommentsById;
    }

    @OneToMany(mappedBy = "assignmentByAssignmentId")
    public Collection<Assignment_StukYearCodeEntity> getAssignmentStukYearCodesById() {
        return assignmentStukYearCodesById;
    }

    public void setAssignmentStukYearCodesById(Collection<Assignment_StukYearCodeEntity> assignmentStukYearCodesById) {
        this.assignmentStukYearCodesById = assignmentStukYearCodesById;
    }

    @OneToMany(mappedBy = "assignmentByAssignmentId")
    public Collection<AssignmentServiceProviderEntity> getAssignmentServiceProvidersById() {
        return assignmentServiceProvidersById;
    }

    public void setAssignmentServiceProvidersById(Collection<AssignmentServiceProviderEntity> assignmentServiceProvidersById) {
        this.assignmentServiceProvidersById = assignmentServiceProvidersById;
    }

    @OneToMany(mappedBy = "assignmentByAssignmentId")
    public Collection<PdfSignatureLinkEntity> getPdfSignatureLinksById() {
        return pdfSignatureLinksById;
    }

    public void setPdfSignatureLinksById(Collection<PdfSignatureLinkEntity> pdfSignatureLinksById) {
        this.pdfSignatureLinksById = pdfSignatureLinksById;
    }
}
