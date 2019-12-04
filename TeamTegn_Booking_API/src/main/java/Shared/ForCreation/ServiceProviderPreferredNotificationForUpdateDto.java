package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderPreferredNotificationForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private String notificationType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }


}
