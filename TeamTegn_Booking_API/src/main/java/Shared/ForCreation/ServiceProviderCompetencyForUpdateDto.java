package Shared.ForCreation;


import javax.validation.constraints.NotNull;

public class ServiceProviderCompetencyForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String competency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }


}
