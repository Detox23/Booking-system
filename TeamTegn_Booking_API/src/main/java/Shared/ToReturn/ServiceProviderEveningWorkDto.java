package Shared.ToReturn;

public class ServiceProviderEveningWorkDto {
    private int id;
    private int serviceProviderId;
    private String weekDay;
    private String eveningWorkPrioritisation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getEveningWorkPrioritisation() {
        return eveningWorkPrioritisation;
    }

    public void setEveningWorkPrioritisation(String eveningWorkPrioritisation) {
        this.eveningWorkPrioritisation = eveningWorkPrioritisation;
    }
}
