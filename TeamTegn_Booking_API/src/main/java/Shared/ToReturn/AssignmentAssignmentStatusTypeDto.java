package Shared.ToReturn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class AssignmentAssignmentStatusTypeDto {
    private int id;
    private int assignmentId;
    private int assignmentStatusTypeId;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp dateCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getAssignmentStatusTypeId() {
        return assignmentStatusTypeId;
    }

    public void setAssignmentStatusTypeId(int assignmentStatusTypeId) {
        this.assignmentStatusTypeId = assignmentStatusTypeId;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
