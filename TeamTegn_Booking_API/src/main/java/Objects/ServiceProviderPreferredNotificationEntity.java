package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceProviderPreferredNotification", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderPreferredNotificationEntity {
    private int id;
    private String notificationType;

    @Basic
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NotificationType")
    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderPreferredNotificationEntity that = (ServiceProviderPreferredNotificationEntity) o;

        if (id != that.id) return false;
        if (notificationType != null ? !notificationType.equals(that.notificationType) : that.notificationType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (notificationType != null ? notificationType.hashCode() : 0);
        return result;
    }
}
