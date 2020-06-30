package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_EveningWork", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderEveningWorkEntity {
    private int serviceProviderId;
    private int weekDayId;
    private int eveningWorkPrioritisationId;

    @Id
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "WeekDayID", nullable = false)
    public int getWeekDayId() {
        return weekDayId;
    }

    public void setWeekDayId(int weekDayId) {
        this.weekDayId = weekDayId;
    }

    @Basic
    @Column(name = "EveningWorkPrioritisationID", nullable = false)
    public int getEveningWorkPrioritisationId() {
        return eveningWorkPrioritisationId;
    }

    public void setEveningWorkPrioritisationId(int eveningWorkPrioritisationId) {
        this.eveningWorkPrioritisationId = eveningWorkPrioritisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderEveningWorkEntity that = (ServiceProviderEveningWorkEntity) o;

        if (serviceProviderId != that.serviceProviderId) return false;
        if (weekDayId != that.weekDayId) return false;
        return eveningWorkPrioritisationId == that.eveningWorkPrioritisationId;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + weekDayId;
        result = 31 * result + eveningWorkPrioritisationId;
        return result;
    }
}
