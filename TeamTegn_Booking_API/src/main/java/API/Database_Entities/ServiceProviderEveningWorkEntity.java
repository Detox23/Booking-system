package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_EveningWork", schema = "dbo")
public class ServiceProviderEveningWorkEntity {
    private Integer weekDayId;
    private ServiceProviderEntity serviceProviderByServiceProviderId;
    private EveningWorkPrioritisationEntity eveningWorkPrioritisationByEveningWorkPrioritisationId;

    @Basic
    @Column(name = "WeekDayID", nullable = false)
    public Integer getWeekDayId() {
        return weekDayId;
    }

    public void setWeekDayId(Integer weekDayId) {
        this.weekDayId = weekDayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderEveningWorkEntity that = (ServiceProviderEveningWorkEntity) o;

        if (weekDayId != null ? !weekDayId.equals(that.weekDayId) : that.weekDayId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return weekDayId != null ? weekDayId.hashCode() : 0;
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
    @JoinColumn(name = "EveningWorkPrioritisationID", referencedColumnName = "ID", nullable = false)
    public EveningWorkPrioritisationEntity getEveningWorkPrioritisationByEveningWorkPrioritisationId() {
        return eveningWorkPrioritisationByEveningWorkPrioritisationId;
    }

    public void setEveningWorkPrioritisationByEveningWorkPrioritisationId(EveningWorkPrioritisationEntity eveningWorkPrioritisationByEveningWorkPrioritisationId) {
        this.eveningWorkPrioritisationByEveningWorkPrioritisationId = eveningWorkPrioritisationByEveningWorkPrioritisationId;
    }
}
