package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentStukYearCodeForCreationDto {

    @NotNull
    private String stukYearCodeName;

    public String getStukYearCodeName() {
        return stukYearCodeName;
    }

    public void setStukYearCodeName(String stukYearCodeName) {
        this.stukYearCodeName = stukYearCodeName;
    }

}
