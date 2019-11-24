package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProviderType", schema = "dbo")
public class ServiceProviderTypeEntity {
    private int id;
    private String providerType;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ProviderType", length = 50)
    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderTypeEntity that = (ServiceProviderTypeEntity) o;

        if (id != that.id) return false;
        return providerType != null ? providerType.equals(that.providerType) : that.providerType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (providerType != null ? providerType.hashCode() : 0);
        return result;
    }
}
