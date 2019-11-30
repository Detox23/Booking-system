package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentInterpretationTypeForCreationDto {
    @NotNull
    private String interpretationTypeName;

    public String getInterpretationTypeName() {
        return interpretationTypeName;
    }

    public void setInterpretationTypeName(String interpretationTypeName) {
        this.interpretationTypeName = interpretationTypeName;
    }

}
