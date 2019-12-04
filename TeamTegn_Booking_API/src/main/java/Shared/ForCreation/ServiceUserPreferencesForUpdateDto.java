package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceUserPreferencesForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
