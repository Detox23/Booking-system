package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider_EveningWork", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderEveningWorkEntity {
    private int id;
    private int serviceProviderId;
    private int weekDayId;
    private int eveningWorkPrioritisationId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
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

        if (id != that.id) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (weekDayId != that.weekDayId) return false;
        if (eveningWorkPrioritisationId != that.eveningWorkPrioritisationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceProviderId;
        result = 31 * result + weekDayId;
        result = 31 * result + eveningWorkPrioritisationId;
        return result;
    }
}
