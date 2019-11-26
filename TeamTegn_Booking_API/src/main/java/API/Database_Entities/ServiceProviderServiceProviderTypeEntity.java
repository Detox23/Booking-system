package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_ServiceProviderType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderServiceProviderTypeEntity {
    private int id;
    private int serviceProviderId;
    private int serviceProviderTypeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        if (id != that.id) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (serviceProviderTypeId != that.serviceProviderTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceProviderId;
        result = 31 * result + serviceProviderTypeId;
        return result;
    }
}
