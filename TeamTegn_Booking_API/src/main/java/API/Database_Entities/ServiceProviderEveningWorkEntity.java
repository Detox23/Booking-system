package API.Database_Entities;

import javax.persistence.*;

@Entity

@Table(name = "ServiceProvider_EveningWork", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderEveningWorkEntity {
    private int id;
    private int serviceProviderId;
    private String weekDay;
    private int eveningWorkPrioritisationId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "WeekDay", nullable = false)
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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
        if (eveningWorkPrioritisationId != that.eveningWorkPrioritisationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceProviderId;
        result = 31 * result + eveningWorkPrioritisationId;
        return result;
    }
}
