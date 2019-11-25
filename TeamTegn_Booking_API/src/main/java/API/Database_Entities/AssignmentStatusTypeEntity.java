package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AssignmentStatusType", schema = "dbo")
public class AssignmentStatusTypeEntity {
    private Integer id;
    private String assignmentStatusTypeName;
    private Integer displayOrder;
    private Boolean isDeleted;
    private Collection<AssignmentAssignmentStatusTypeEntity> assignmentAssignmentStatusTypesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Basic
    @Column(name = "IsDeleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentStatusTypeEntity that = (AssignmentStatusTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (assignmentStatusTypeName != null ? !assignmentStatusTypeName.equals(that.assignmentStatusTypeName) : that.assignmentStatusTypeName != null)
            return false;
        if (displayOrder != null ? !displayOrder.equals(that.displayOrder) : that.displayOrder != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assignmentStatusTypeName != null ? assignmentStatusTypeName.hashCode() : 0);
        result = 31 * result + (displayOrder != null ? displayOrder.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "assignmentStatusTypeByAssignmentStatusTypeId")
    public Collection<AssignmentAssignmentStatusTypeEntity> getAssignmentAssignmentStatusTypesById() {
        return assignmentAssignmentStatusTypesById;
    }

    public void setAssignmentAssignmentStatusTypesById(Collection<AssignmentAssignmentStatusTypeEntity> assignmentAssignmentStatusTypesById) {
        this.assignmentAssignmentStatusTypesById = assignmentAssignmentStatusTypesById;
    }
}
