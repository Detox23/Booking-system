package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ServiceProviderType", schema = "dbo")
public class ServiceProviderTypeEntity {
    private Integer id;
    private String providerType;
    private Boolean isDeleted;
    private Collection<ServiceProviderServiceProviderTypeEntity> serviceProviderServiceProviderTypesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ProviderType", nullable = true, length = 50)
    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
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

        ServiceProviderTypeEntity that = (ServiceProviderTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (providerType != null ? !providerType.equals(that.providerType) : that.providerType != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (providerType != null ? providerType.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "serviceProviderTypeByServiceProviderTypeId")
    public Collection<ServiceProviderServiceProviderTypeEntity> getServiceProviderServiceProviderTypesById() {
        return serviceProviderServiceProviderTypesById;
    }

    public void setServiceProviderServiceProviderTypesById(Collection<ServiceProviderServiceProviderTypeEntity> serviceProviderServiceProviderTypesById) {
        this.serviceProviderServiceProviderTypesById = serviceProviderServiceProviderTypesById;
    }
}
