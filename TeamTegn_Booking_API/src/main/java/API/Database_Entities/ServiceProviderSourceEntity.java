package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ServiceProviderSource", schema = "dbo")
public class ServiceProviderSourceEntity {
    private Integer id;
    private String providerSource;
    private Boolean isDeleted;
    private Collection<ServiceProviderEntity> serviceProvidersById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ProviderSource", nullable = true, length = 50)
    public String getProviderSource() {
        return providerSource;
    }

    public void setProviderSource(String providerSource) {
        this.providerSource = providerSource;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderSourceEntity that = (ServiceProviderSourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (providerSource != null ? !providerSource.equals(that.providerSource) : that.providerSource != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (providerSource != null ? providerSource.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "serviceProviderSourceBySource")
    public Collection<ServiceProviderEntity> getServiceProvidersById() {
        return serviceProvidersById;
    }

    public void setServiceProvidersById(Collection<ServiceProviderEntity> serviceProvidersById) {
        this.serviceProvidersById = serviceProvidersById;
    }
}
