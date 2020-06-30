package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProviderPreferredNotification", schema = "dbo")
public class ServiceProviderPreferredNotificationEntity {
    private int id;
    private String notificationType;
    private boolean isDeleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NotificationType", nullable = true, length = 250)
    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderPreferredNotificationEntity that = (ServiceProviderPreferredNotificationEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        return notificationType != null ? notificationType.equals(that.notificationType) : that.notificationType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (notificationType != null ? notificationType.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
