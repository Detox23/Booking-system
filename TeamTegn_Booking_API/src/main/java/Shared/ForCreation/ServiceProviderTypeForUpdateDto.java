package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderTypeForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String providerType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }
}
