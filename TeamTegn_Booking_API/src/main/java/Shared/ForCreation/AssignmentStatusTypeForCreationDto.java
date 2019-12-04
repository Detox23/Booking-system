package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentStatusTypeForCreationDto {
    @NotNull
    private String assignmentStatusTypeName;
    @NotNull
    private Integer displayOrder;

    public String getAssignmentStatusTypeName() {
        return assignmentStatusTypeName;
    }

    public void setAssignmentStatusTypeName(String assignmentStatusTypeName) {
        this.assignmentStatusTypeName = assignmentStatusTypeName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
