package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ServiceProviderHasWheels", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderHasWheelsEntity {
    private int id;
    private int serviceProviderId;
    private int transportTypeId;
    private Timestamp fromDate;
    private Timestamp fromTime;
    private Timestamp toDate;
    private Timestamp toTime;

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
    @Column(name = "TransportTypeID", nullable = false)
    public int getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(int transportTypeId) {
        this.transportTypeId = transportTypeId;
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
    @Column(name = "ToDate", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderHasWheelsEntity that = (ServiceProviderHasWheelsEntity) o;

        if (id != that.id) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (transportTypeId != that.transportTypeId) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (fromTime != null ? !fromTime.equals(that.fromTime) : that.fromTime != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        return toTime != null ? toTime.equals(that.toTime) : that.toTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceProviderId;
        result = 31 * result + transportTypeId;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (fromTime != null ? fromTime.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (toTime != null ? toTime.hashCode() : 0);
        return result;
    }
}
