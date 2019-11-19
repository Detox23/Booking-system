package API.Database_Entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentStatus", schema = "dbo")
public class AssignmentStatusEntity {
    private int id;
    private String assignmentStatusName;
    private boolean isDeleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "IsDeleted", nullable = false, columnDefinition = "boolean default false")
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentStatusEntity that = (AssignmentStatusEntity) o;

        if (id != that.id) return false;
        return assignmentStatusName != null ? assignmentStatusName.equals(that.assignmentStatusName) : that.assignmentStatusName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentStatusName != null ? assignmentStatusName.hashCode() : 0);
        return result;
    }
}
