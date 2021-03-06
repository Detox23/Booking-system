package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_VocalLanguage", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderVocalLanguageEntity {
    private int serviceProviderId;
    private int vocalLanguageId;

    @Id
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "VocalLanguageID", nullable = false)
    public int getVocalLanguageId() {
        return vocalLanguageId;
    }

    public void setVocalLanguageId(int vocalLanguageId) {
        this.vocalLanguageId = vocalLanguageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderVocalLanguageEntity that = (ServiceProviderVocalLanguageEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        return vocalLanguageId == that.vocalLanguageId;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + vocalLanguageId;
        return result;
    }
}
