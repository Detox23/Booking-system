package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceUserStatusForCreationDto {
    @NotNull
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
