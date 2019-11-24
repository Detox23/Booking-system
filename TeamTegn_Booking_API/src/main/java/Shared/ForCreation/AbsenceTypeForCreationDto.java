package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AbsenceTypeForCreationDto {
    @NotNull
    private String absenceTypeName;

    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
    }
}
