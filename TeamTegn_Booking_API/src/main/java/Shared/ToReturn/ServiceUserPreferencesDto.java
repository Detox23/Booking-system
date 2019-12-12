package Shared.ToReturn;

public class ServiceUserPreferencesDto {
    private int id;
    private int serviceUserId;
    private int serviceProviderId;
    private ServiceProviderDto serviceProvider;
    private int rating;

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public ServiceProviderDto getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProviderDto serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
