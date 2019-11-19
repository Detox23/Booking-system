package API.Database_Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Grant_Assignment", schema = "dbo")
public class GrantAssignmentEntity implements Serializable {
    private GrantEntity grantByGrantId;
    private AssignmentEntity assignmentByAssignmentId;

    @Id
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
