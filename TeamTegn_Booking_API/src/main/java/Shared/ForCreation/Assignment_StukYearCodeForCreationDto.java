package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class Assignment_StukYearCodeForCreationDto {
    @NotNull
    private int assignmentId;
    @NotNull
    private int stukYearCodeId;

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


}
