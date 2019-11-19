package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Assignment_STUKYearCode", schema = "dbo")
public class AssignmentStukYearCodeEntity {
    private int id;
    private int assignmentId;
    private int stukYearCodeId;
    private Timestamp dateCreated;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentID", nullable = false)
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Basic
    @Column(name = "StukYearCodeID", nullable = false)
    public int getStukYearCodeId() {
        return stukYearCodeId;
    }

    public void setStukYearCodeId(int stukYearCodeId) {
        this.stukYearCodeId = stukYearCodeId;
    }

    @Basic
    @Column(name = "DateCreated", nullable = true)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentStukYearCodeEntity that = (AssignmentStukYearCodeEntity) o;

        if (id != that.id) return false;
        if (assignmentId != that.assignmentId) return false;
        if (stukYearCodeId != that.stukYearCodeId) return false;
        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + assignmentId;
        result = 31 * result + stukYearCodeId;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
