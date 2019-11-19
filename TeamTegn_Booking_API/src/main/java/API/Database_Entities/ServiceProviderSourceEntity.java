package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProviderSource", schema = "dbo")
public class ServiceProviderSourceEntity {
    private int id;
    private String providerSource;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderSourceEntity that = (ServiceProviderSourceEntity) o;

        if (id != that.id) return false;
        return providerSource != null ? providerSource.equals(that.providerSource) : that.providerSource == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (providerSource != null ? providerSource.hashCode() : 0);
        return result;
    }
}
