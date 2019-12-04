package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceUserPreferencesForCreationDto {
    @NotNull
    private int rating;
    @NotNull
    private int serviceProviderId;

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
