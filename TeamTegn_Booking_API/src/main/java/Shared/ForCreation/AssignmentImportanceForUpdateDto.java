package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentImportanceForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String importanceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
    }
}
