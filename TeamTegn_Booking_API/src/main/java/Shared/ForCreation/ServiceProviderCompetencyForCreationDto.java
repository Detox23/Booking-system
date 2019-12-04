package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderCompetencyForCreationDto {
    @NotNull
    private String competency;

    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }

}
