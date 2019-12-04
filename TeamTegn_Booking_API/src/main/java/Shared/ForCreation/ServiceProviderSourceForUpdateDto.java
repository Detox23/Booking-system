package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderSourceForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String providerSource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProviderSource() {
        return providerSource;
    }

    public void setProviderSource(String providerSource) {
        this.providerSource = providerSource;
    }


}
