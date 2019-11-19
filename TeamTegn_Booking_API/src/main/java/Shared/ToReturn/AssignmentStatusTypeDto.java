package Shared.ToReturn;

public class AssignmentStatusTypeDto {
    private int id;
    private String assignmentStatusTypeName;
    private Integer displayOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
