package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_ServiceProviderType", schema = "dbo")
public class ServiceProviderServiceProviderTypeEntity {
    private int serviceProviderId;
    private int serviceProviderTypeId;

    @Id
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "ServiceProviderTypeID", nullable = false)
    public int getServiceProviderTypeId() {
        return serviceProviderTypeId;
    }

    public void setServiceProviderTypeId(int serviceProviderTypeId) {
        this.serviceProviderTypeId = serviceProviderTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderServiceProviderTypeEntity that = (ServiceProviderServiceProviderTypeEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        return serviceProviderTypeId == that.serviceProviderTypeId;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + serviceProviderTypeId;
        return result;
    }
}
