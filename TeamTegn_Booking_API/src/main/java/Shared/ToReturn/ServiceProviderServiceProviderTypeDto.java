package Shared.ToReturn;

public class ServiceProviderServiceProviderTypeDto {
    private int id;
    private int serviceProviderId;
    private int serviceProviderTypeId;

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public int getServiceProviderTypeId() {
        return serviceProviderTypeId;
    }

    public void setServiceProviderTypeId(int serviceProviderTypeId) {
        this.serviceProviderTypeId = serviceProviderTypeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
