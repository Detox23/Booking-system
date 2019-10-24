package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AssignmentStatus", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentStatusEntity {
    private int id;
    private String assignmentStatusName;

    @Basic
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentStatusName")
    public String getAssignmentStatusName() {
        return assignmentStatusName;
    }

    public void setAssignmentStatusName(String assignmentStatusName) {
        this.assignmentStatusName = assignmentStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentStatusEntity that = (AssignmentStatusEntity) o;

        if (id != that.id) return false;
        if (assignmentStatusName != null ? !assignmentStatusName.equals(that.assignmentStatusName) : that.assignmentStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentStatusName != null ? assignmentStatusName.hashCode() : 0);
        return result;
    }
}
