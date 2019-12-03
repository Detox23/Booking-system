package Shared.ForCreation;

public class ServiceUserPreferencesForCreationDto {
    private int rating;
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
