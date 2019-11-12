package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Assignment_AssignmentStatusType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentAssignmentStatusTypeEntity {
    private int id;
    private int assignmentId;
    private int assignmentStatusTypeId;
    private Timestamp dateCreated;


    @Id
    @Basic
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
    @Column(name = "AssignmentStatusTypeID", nullable = false)
    public int getAssignmentStatusTypeId() {
        return assignmentStatusTypeId;
    }

    public void setAssignmentStatusTypeId(int assignmentStatusTypeId) {
        this.assignmentStatusTypeId = assignmentStatusTypeId;
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

        AssignmentAssignmentStatusTypeEntity that = (AssignmentAssignmentStatusTypeEntity) o;

        if (id != that.id) return false;
        if (assignmentId != that.assignmentId) return false;
        if (assignmentStatusTypeId != that.assignmentStatusTypeId) return false;
        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
    }

}
