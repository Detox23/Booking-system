package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TransportType", schema = "dbo")
public class TransportTypeEntity {
    private Integer id;
    private String transport;
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
    @Column(name = "Transport", nullable = true, length = 50)
    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
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

        TransportTypeEntity that = (TransportTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transport != null ? !transport.equals(that.transport) : that.transport != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "transportTypeByTransportId")
    public Collection<ServiceProviderEntity> getServiceProvidersById() {
        return serviceProvidersById;
    }

    public void setServiceProvidersById(Collection<ServiceProviderEntity> serviceProvidersById) {
        this.serviceProvidersById = serviceProvidersById;
    }
}
