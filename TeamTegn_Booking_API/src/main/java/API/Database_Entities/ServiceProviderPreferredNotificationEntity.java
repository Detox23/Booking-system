package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ServiceProviderPreferredNotification", schema = "dbo")
public class ServiceProviderPreferredNotificationEntity {
    private Integer id;
    private String notificationType;
    private Boolean isDeleted;
    private Collection<ServiceProviderEntity> serviceProvidersById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderPreferredNotificationEntity that = (ServiceProviderPreferredNotificationEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (notificationType != null ? !notificationType.equals(that.notificationType) : that.notificationType != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (notificationType != null ? notificationType.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "serviceProviderPreferredNotificationByPreferredNotificationId")
    public Collection<ServiceProviderEntity> getServiceProvidersById() {
        return serviceProvidersById;
    }

    public void setServiceProvidersById(Collection<ServiceProviderEntity> serviceProvidersById) {
        this.serviceProvidersById = serviceProvidersById;
    }
}
