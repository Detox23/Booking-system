package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceProvider_EveningWork", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderEveningWorkEntity {
    private int serviceProviderId;
    private int weekDayId;
    private int eveningWorkPrioritisationId;

    @Basic
    @Column(name = "ServiceProviderID")
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "WeekDayID")
    public int getWeekDayId() {
        return weekDayId;
    }

    public void setWeekDayId(int weekDayId) {
        this.weekDayId = weekDayId;
    }

    @Basic
    @Column(name = "EveningWorkPrioritisationID")
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
        if (eveningWorkPrioritisationId != that.eveningWorkPrioritisationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId;
        result = 31 * result + weekDayId;
        result = 31 * result + eveningWorkPrioritisationId;
        return result;
    }
}
