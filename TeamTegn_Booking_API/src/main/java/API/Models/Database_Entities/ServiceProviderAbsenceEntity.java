package API.Models.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ServiceProviderAbsence", schema = "dbo")
public class ServiceProviderAbsenceEntity {
    private int id;
    private int serviceProviderId;
    private Integer absenceTypeId;
    private String absenceReason;
    private Date fromDate;
    private Time fromTime;
    private Date toDate;
    private Time toTime;
    private Float absenceDays;
    private Integer createdBy;
    private Timestamp createdDate;

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
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "FromTime", nullable = false)
    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    @Basic
    @Column(name = "ToDate", nullable = false)
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Basic
    @Column(name = "ToTime", nullable = false)
    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    @Basic
    @Column(name = "AbsenceHours", nullable = true)
    public Float getAbsenceHours() {
        return absenceDays;
    }

    public void setAbsenceHours(Float absenceDays) {
        this.absenceDays = absenceDays;
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
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
