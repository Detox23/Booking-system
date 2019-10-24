package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "NotificationType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class NotificationTypeEntity {
    private int id;
    private String notificationType;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NotificationType", nullable = true, length = 50)
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

        NotificationTypeEntity that = (NotificationTypeEntity) o;

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
