package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentTypeEntity {
    private int id;
    private String assignmentTypeName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentTypeName", nullable = true, length = 50)
    public String getAssignmentTypeName() {
        return assignmentTypeName;
    }

    public void setAssignmentTypeName(String assignmentTypeName) {
        this.assignmentTypeName = assignmentTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentTypeEntity that = (AssignmentTypeEntity) o;

        if (id != that.id) return false;
        return assignmentTypeName != null ? assignmentTypeName.equals(that.assignmentTypeName) : that.assignmentTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentTypeName != null ? assignmentTypeName.hashCode() : 0);
        return result;
    }
}
