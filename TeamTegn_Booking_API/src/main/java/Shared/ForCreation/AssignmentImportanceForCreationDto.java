package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentImportanceForCreationDto {
    @NotNull
    private String importanceName;

    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
    }

}
