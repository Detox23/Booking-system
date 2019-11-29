package Shared.ToReturn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.util.Date;

public class AssignmentViewDto {
    private int id;
    private Integer assignmentTypeId;
    private Integer importanceId;
    private Integer interpretationTypeId;
    private String assignmentTitle;
    private String assignmentPlace;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date assignmentDate;
    @JsonFormat(timezone ="Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date assignmentEndDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time endTime;
    private int totalTime;




    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(Integer assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    public Integer getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Integer importanceId) {
        this.importanceId = importanceId;
    }

    public Integer getInterpretationTypeId() {
        return interpretationTypeId;
    }

    public void setInterpretationTypeId(Integer interpretationTypeId) {
        this.interpretationTypeId = interpretationTypeId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentPlace() {
        return assignmentPlace;
    }

    public void setAssignmentPlace(String assignmentPlace) {
        this.assignmentPlace = assignmentPlace;
    }

}
