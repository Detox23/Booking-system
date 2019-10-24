package Objects.Factory.Database_Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceProvider_ServiceProviderOtherPreference", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderServiceProviderOtherPreferenceEntity {
    private int serviceProviderId;
    private int otherPreferenceId;

    @Basic
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "OtherPreferenceID", nullable = false)
    public int getOtherPreferenceId() {
        return otherPreferenceId;
    }

    public void setOtherPreferenceId(int otherPreferenceId) {
        this.otherPreferenceId = otherPreferenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderServiceProviderOtherPreferenceEntity that = (ServiceProviderServiceProviderOtherPreferenceEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        if (otherPreferenceId != that.otherPreferenceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + otherPreferenceId;
        return result;
    }
}
