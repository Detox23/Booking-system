package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentTitleForUpdateDto {

    @NotNull
    private int id;

    @NotNull
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
