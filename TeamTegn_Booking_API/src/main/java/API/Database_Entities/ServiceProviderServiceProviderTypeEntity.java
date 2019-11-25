package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_ServiceProviderType", schema = "dbo")
public class ServiceProviderServiceProviderTypeEntity {
    private Integer id;
    private ServiceProviderEntity serviceProviderByServiceProviderId;
    private ServiceProviderTypeEntity serviceProviderTypeByServiceProviderTypeId;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderServiceProviderTypeEntity that = (ServiceProviderServiceProviderTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
    @JoinColumn(name = "ServiceProviderTypeID", referencedColumnName = "ID", nullable = false)
    public ServiceProviderTypeEntity getServiceProviderTypeByServiceProviderTypeId() {
        return serviceProviderTypeByServiceProviderTypeId;
    }

    public void setServiceProviderTypeByServiceProviderTypeId(ServiceProviderTypeEntity serviceProviderTypeByServiceProviderTypeId) {
        this.serviceProviderTypeByServiceProviderTypeId = serviceProviderTypeByServiceProviderTypeId;
    }
}
