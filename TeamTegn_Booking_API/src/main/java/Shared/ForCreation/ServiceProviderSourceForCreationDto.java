package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderSourceForCreationDto {

    @NotNull
    private String providerSource;


    public String getProviderSource() {
        return providerSource;
    }

    public void setProviderSource(String providerSource) {
        this.providerSource = providerSource;
    }


}
