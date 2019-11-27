package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderEveningWorkForCreationDto {
    @NotNull
    private int serviceProviderId;
    @NotNull
    private String weekDay;
    @NotNull
    private int eveningWorkPrioritisationId;

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

    public int getEveningWorkPrioritisationId() {
        return eveningWorkPrioritisationId;
    }

    public void setEveningWorkPrioritisationId(int eveningWorkPrioritisationId) {
        this.eveningWorkPrioritisationId = eveningWorkPrioritisationId;
    }

}
