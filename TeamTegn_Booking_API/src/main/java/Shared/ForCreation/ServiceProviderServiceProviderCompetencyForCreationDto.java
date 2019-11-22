package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderServiceProviderCompetencyForCreationDto {

    @NotNull
    private int serviceProviderId;
    @NotNull
    private int competencyId;

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }


    public int getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
    }



}
