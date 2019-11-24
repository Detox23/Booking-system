package Shared.ForCreation;


import javax.validation.constraints.NotNull;

public class ServiceProviderTypeForCreationDto {

    @NotNull
    private String providerType;

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }
}
