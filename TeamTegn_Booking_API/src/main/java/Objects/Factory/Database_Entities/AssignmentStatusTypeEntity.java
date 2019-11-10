package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentStatusType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentStatusTypeEntity {
    private int id;
    private String assignmentStatusTypeName;
    private Integer displayOrder;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentStatusTypeName", nullable = false, length = 150)
    public String getAssignmentStatusTypeName() {
        return assignmentStatusTypeName;
    }

    public void setAssignmentStatusTypeName(String assignmentStatusTypeName) {
        this.assignmentStatusTypeName = assignmentStatusTypeName;
    }

    @Basic
    @Column(name = "DisplayOrder", nullable = true)
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentStatusTypeEntity that = (AssignmentStatusTypeEntity) o;

        if (id != that.id) return false;
        if (assignmentStatusTypeName != null ? !assignmentStatusTypeName.equals(that.assignmentStatusTypeName) : that.assignmentStatusTypeName != null)
            return false;
        return displayOrder != null ? displayOrder.equals(that.displayOrder) : that.displayOrder == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentStatusTypeName != null ? assignmentStatusTypeName.hashCode() : 0);
        result = 31 * result + (displayOrder != null ? displayOrder.hashCode() : 0);
        return result;
    }
}
