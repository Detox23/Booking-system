package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceUserPreferences", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceUserPreferencesEntity {
    private int id;
    private int serviceUserId;
    private int serviceProviderId;
    private int rating;

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
    @Column(name = "ServiceUserID", nullable = false)
    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    @Basic
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "Rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceUserPreferencesEntity that = (ServiceUserPreferencesEntity) o;

        if (id != that.id) return false;
        if (serviceUserId != that.serviceUserId) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (rating != that.rating) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceUserId;
        result = 31 * result + serviceProviderId;
        result = 31 * result + rating;
        return result;
    }
}
