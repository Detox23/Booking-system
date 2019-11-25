package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AssignmentImportance", schema = "dbo")
public class AssignmentImportanceEntity {
    private Integer id;
    private String importanceName;
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
    @Column(name = "ImportanceName", nullable = true, length = 250)
    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
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

        AssignmentImportanceEntity that = (AssignmentImportanceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (importanceName != null ? !importanceName.equals(that.importanceName) : that.importanceName != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (importanceName != null ? importanceName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "assignmentImportanceByImportanceId")
    public Collection<AssignmentEntity> getAssignmentsById() {
        return assignmentsById;
    }

    public void setAssignmentsById(Collection<AssignmentEntity> assignmentsById) {
        this.assignmentsById = assignmentsById;
    }
}
