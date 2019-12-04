package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceUserStatusForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
