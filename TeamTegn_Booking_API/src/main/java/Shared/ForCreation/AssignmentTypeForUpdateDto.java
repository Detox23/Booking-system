package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentTypeForUpdateDto {
    private int id;
    @NotNull
    private String assignmentTypeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignmentTypeName() {
        return assignmentTypeName;
    }

    public void setAssignmentTypeName(String assignmentTypeName) {
        this.assignmentTypeName = assignmentTypeName;
    }
}

