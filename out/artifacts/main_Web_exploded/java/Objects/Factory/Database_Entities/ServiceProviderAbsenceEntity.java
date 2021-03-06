package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ServiceProviderAbsence", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderAbsenceEntity {
    private int id;
    private int serviceProviderId;
    private Integer absenceTypeId;
    private String absenceReason;
    private Timestamp fromDate;
    private Timestamp fromTime;
    private Timestamp toDate;
    private Timestamp toTime;
    private Integer absenceDays;
    private String createdBy;
    private Timestamp createdDate;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "AbsenceTypeID", nullable = true)
    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    @Basic
    @Column(name = "AbsenceReason", nullable = true, length = 500)
    public String getAbsenceReason() {
        return absenceReason;
    }

    public void setAbsenceReason(String absenceReason) {
        this.absenceReason = absenceReason;
    }

    @Basic
    @Column(name = "FromDate", nullable = false)
    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "FromTime", nullable = true)
    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    @Basic
    @Column(name = "ToDate", nullable = true)
    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    @Basic
    @Column(name = "ToTime", nullable = true)
    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }

    @Basic
    @Column(name = "AbsenceDays", nullable = true)
    public Integer getAbsenceDays() {
        return absenceDays;
    }

    public void setAbsenceDays(Integer absenceDays) {
        this.absenceDays = absenceDays;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = true, length = 50)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderAbsenceEntity that = (ServiceProviderAbsenceEntity) o;

        if (id != that.id) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (absenceTypeId != null ? !absenceTypeId.equals(that.absenceTypeId) : that.absenceTypeId != null)
            return false;
        if (absenceReason != null ? !absenceReason.equals(that.absenceReason) : that.absenceReason != null)
            return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (fromTime != null ? !fromTime.equals(that.fromTime) : that.fromTime != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        if (toTime != null ? !toTime.equals(that.toTime) : that.toTime != null) return false;
        if (absenceDays != null ? !absenceDays.equals(that.absenceDays) : that.absenceDays != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return createdDate != null ? createdDate.equals(that.createdDate) : that.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceProviderId;
        result = 31 * result + (absenceTypeId != null ? absenceTypeId.hashCode() : 0);
        result = 31 * result + (absenceReason != null ? absenceReason.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (fromTime != null ? fromTime.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (toTime != null ? toTime.hashCode() : 0);
        result = 31 * result + (absenceDays != null ? absenceDays.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
