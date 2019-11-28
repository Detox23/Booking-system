package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentStukYearCodeForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String stukYearCodeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStukYearCodeName() {
        return stukYearCodeName;
    }

    public void setStukYearCodeName(String stukYearCodeName) {
        this.stukYearCodeName = stukYearCodeName;
    }
}
