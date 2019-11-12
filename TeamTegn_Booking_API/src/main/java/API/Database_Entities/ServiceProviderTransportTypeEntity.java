package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_TransportType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderTransportTypeEntity {
    private int serviceProviderId;
    private Integer transportTypeId;

    @Id
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "TransportTypeID", nullable = true)
    public Integer getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(Integer transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderTransportTypeEntity that = (ServiceProviderTransportTypeEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        return transportTypeId != null ? transportTypeId.equals(that.transportTypeId) : that.transportTypeId == null;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + (transportTypeId != null ? transportTypeId.hashCode() : 0);
        return result;
    }
}
