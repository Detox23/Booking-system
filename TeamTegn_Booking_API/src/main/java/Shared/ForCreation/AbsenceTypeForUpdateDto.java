package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AbsenceTypeForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String absenceTypeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
    }
}
