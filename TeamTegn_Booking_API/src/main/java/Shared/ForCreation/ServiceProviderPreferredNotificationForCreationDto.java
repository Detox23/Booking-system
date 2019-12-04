package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderPreferredNotificationForCreationDto {
    @NotNull
    private String notificationType;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

}
