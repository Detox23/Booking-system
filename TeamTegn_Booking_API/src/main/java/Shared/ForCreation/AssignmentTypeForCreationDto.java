package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentTypeForCreationDto {
    @NotNull
    private String assignmentTypeName;


    public String getAssignmentTypeName() {
        return assignmentTypeName;
    }

    public void setAssignmentTypeName(String assignmentTypeName) {
        this.assignmentTypeName = assignmentTypeName;
    }
}

