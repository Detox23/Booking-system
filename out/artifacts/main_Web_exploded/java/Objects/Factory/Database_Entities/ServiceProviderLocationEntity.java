package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_Location", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderLocationEntity {
    private int serviceProviderId;
    private int locationId;

    @Id
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "LocationID", nullable = false)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderLocationEntity that = (ServiceProviderLocationEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        return locationId == that.locationId;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + locationId;
        return result;
    }
}
