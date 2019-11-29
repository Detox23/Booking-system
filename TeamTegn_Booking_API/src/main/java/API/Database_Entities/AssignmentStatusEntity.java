package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentStatus", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentStatusEntity {
    private int id;
    private String assignmentStatusName;
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
    @Column(name = "AssignmentStatusName", nullable = false, length = 100)
    public String getAssignmentStatusName() {
        return assignmentStatusName;
    }

    public void setAssignmentStatusName(String assignmentStatusName) {
        this.assignmentStatusName = assignmentStatusName;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
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

        AssignmentStatusEntity that = (AssignmentStatusEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (assignmentStatusName != null ? !assignmentStatusName.equals(that.assignmentStatusName) : that.assignmentStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentStatusName != null ? assignmentStatusName.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
