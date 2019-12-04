package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentStatusForCreationDto {
    @NotNull
    private String assignmentStatusName;


    public String getAssignmentStatusName() {
        return assignmentStatusName;
    }

    public void setAssignmentStatusName(String assignmentStatusName) {
        this.assignmentStatusName = assignmentStatusName;
    }
}
