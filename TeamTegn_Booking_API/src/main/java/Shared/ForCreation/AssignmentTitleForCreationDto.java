package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentTitleForCreationDto {
    @NotNull
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
