package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentStatusForUpdateDto {
    @NotNull
    private int id;

    @NotNull
    private String assignmentStatusName;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAssignmentStatusName() {
        return assignmentStatusName;
    }

    public void setAssignmentStatusName(String assignmentStatusName) {
        this.assignmentStatusName = assignmentStatusName;
    }
}
