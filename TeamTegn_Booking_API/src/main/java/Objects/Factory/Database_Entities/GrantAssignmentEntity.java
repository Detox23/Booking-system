package Objects.Factory.Database_Entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Grant_Assignment", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class GrantAssignmentEntity {
    private GrantEntity grantByGrantId;
    private AssignmentEntity assignmentByAssignmentId;

    @ManyToOne
    @JoinColumn(name = "GrantID", referencedColumnName = "ID", nullable = false)
    public GrantEntity getGrantByGrantId() {
        return grantByGrantId;
    }

    public void setGrantByGrantId(GrantEntity grantByGrantId) {
        this.grantByGrantId = grantByGrantId;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentID", referencedColumnName = "ID", nullable = false)
    public AssignmentEntity getAssignmentByAssignmentId() {
        return assignmentByAssignmentId;
    }

    public void setAssignmentByAssignmentId(AssignmentEntity assignmentByAssignmentId) {
        this.assignmentByAssignmentId = assignmentByAssignmentId;
    }
}
