package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AssignmentStatus", schema = "dbo")
public class AssignmentStatusEntity {
    private Integer id;
    private String assignmentStatusName;
    private Boolean isDeleted;
    private Collection<AssignmentEntity> assignmentsById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentStatusName", nullable = false, length = 100)
    public String getAssignmentStatusName() {
        return assignmentStatusName;
    }

    public void setAssignmentStatusName(String assignmentStatusName) {
        this.assignmentStatusName = assignmentStatusName;
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

        AssignmentStatusEntity that = (AssignmentStatusEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (assignmentStatusName != null ? !assignmentStatusName.equals(that.assignmentStatusName) : that.assignmentStatusName != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assignmentStatusName != null ? assignmentStatusName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "assignmentStatusByAssignmentStatusTypeId")
    public Collection<AssignmentEntity> getAssignmentsById() {
        return assignmentsById;
    }

    public void setAssignmentsById(Collection<AssignmentEntity> assignmentsById) {
        this.assignmentsById = assignmentsById;
    }
}
