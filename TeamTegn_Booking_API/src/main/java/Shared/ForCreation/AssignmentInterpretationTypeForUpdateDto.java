package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentInterpretationTypeForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String interpretationTypeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterpretationTypeName() {
        return interpretationTypeName;
    }

    public void setInterpretationTypeName(String interpretationTypeName) {
        this.interpretationTypeName = interpretationTypeName;
    }

}
