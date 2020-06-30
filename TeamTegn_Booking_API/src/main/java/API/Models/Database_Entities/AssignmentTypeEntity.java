package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentType", schema = "dbo")
public class AssignmentTypeEntity {
    private int id;
    private String assignmentTypeName;
    private boolean isDeleted;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentTypeName", length = 50)
    public String getAssignmentTypeName() {
        return assignmentTypeName;
    }

    public void setAssignmentTypeName(String assignmentTypeName) {
        this.assignmentTypeName = assignmentTypeName;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false, columnDefinition = "bit default 0")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentTypeEntity that = (AssignmentTypeEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        return assignmentTypeName != null ? assignmentTypeName.equals(that.assignmentTypeName) : that.assignmentTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentTypeName != null ? assignmentTypeName.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
