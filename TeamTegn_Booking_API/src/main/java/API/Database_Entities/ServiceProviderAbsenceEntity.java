package API.Database_Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "ServiceProviderAbsence", schema = "dbo")
public class ServiceProviderAbsenceEntity {
    private Integer id;
    private String absenceReason;
    private Date fromDate;
    private Object fromTime;
    private Date toDate;
    private Object toTime;
    private Integer absenceDays;
    private Integer createdBy;
    private Timestamp createdDate;
    private ServiceProviderEntity serviceProviderByServiceProviderId;
    private AbsenceTypeEntity absenceTypeByAbsenceTypeId;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public Object getFromTime() {
        return fromTime;
    }

    public void setFromTime(Object fromTime) {
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
    public Object getToTime() {
        return toTime;
    }

    public void setToTime(Object toTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderAbsenceEntity that = (ServiceProviderAbsenceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (absenceReason != null ? !absenceReason.equals(that.absenceReason) : that.absenceReason != null)
            return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (fromTime != null ? !fromTime.equals(that.fromTime) : that.fromTime != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        if (toTime != null ? !toTime.equals(that.toTime) : that.toTime != null) return false;
        if (absenceDays != null ? !absenceDays.equals(that.absenceDays) : that.absenceDays != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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

    @ManyToOne
    @JoinColumn(name = "ServiceProviderID", referencedColumnName = "ID", nullable = false)
    public ServiceProviderEntity getServiceProviderByServiceProviderId() {
        return serviceProviderByServiceProviderId;
    }

    public void setServiceProviderByServiceProviderId(ServiceProviderEntity serviceProviderByServiceProviderId) {
        this.serviceProviderByServiceProviderId = serviceProviderByServiceProviderId;
    }

    @ManyToOne
    @JoinColumn(name = "AbsenceTypeID", referencedColumnName = "ID")
    public AbsenceTypeEntity getAbsenceTypeByAbsenceTypeId() {
        return absenceTypeByAbsenceTypeId;
    }

    public void setAbsenceTypeByAbsenceTypeId(AbsenceTypeEntity absenceTypeByAbsenceTypeId) {
        this.absenceTypeByAbsenceTypeId = absenceTypeByAbsenceTypeId;
    }
}
