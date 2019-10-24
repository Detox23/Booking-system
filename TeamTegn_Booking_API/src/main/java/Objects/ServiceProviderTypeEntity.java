package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceProviderType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderTypeEntity {
    private int id;
    private String providerType;

    @Basic
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ProviderType")
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
        if (providerType != null ? !providerType.equals(that.providerType) : that.providerType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (providerType != null ? providerType.hashCode() : 0);
        return result;
    }
}
