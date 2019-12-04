package Shared.ToReturn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Assignment_StukYearCodeDto {
    private int id;
    private int assignmentId;
    private int stukYearCodeId;
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

    public int getStukYearCodeId() {
        return stukYearCodeId;
    }

    public void setStukYearCodeId(int stukYearCodeId) {
        this.stukYearCodeId = stukYearCodeId;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
