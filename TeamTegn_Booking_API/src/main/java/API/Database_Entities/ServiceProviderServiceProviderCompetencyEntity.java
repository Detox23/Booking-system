package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_ServiceProviderCompetency", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderServiceProviderCompetencyEntity {
    private int serviceProviderId;
    private int competencyId;

    @Id
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "CompetencyID", nullable = false)
    public int getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderServiceProviderCompetencyEntity that = (ServiceProviderServiceProviderCompetencyEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        return competencyId == that.competencyId;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + competencyId;
        return result;
    }
}
