package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceUserPreferences", schema = "dbo")
public class ServiceUserPreferencesEntity {
    private Integer id;
    private ServiceUserEntity serviceUserByServiceUserId;
    private ServiceProviderEntity serviceProviderByServiceProviderId;
    private RatingEntity ratingByRating;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceUserPreferencesEntity that = (ServiceUserPreferencesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "ServiceUserID", referencedColumnName = "ID", nullable = false)
    public ServiceUserEntity getServiceUserByServiceUserId() {
        return serviceUserByServiceUserId;
    }

    public void setServiceUserByServiceUserId(ServiceUserEntity serviceUserByServiceUserId) {
        this.serviceUserByServiceUserId = serviceUserByServiceUserId;
    }

    @ManyToOne
    @JoinColumn(name = "ServiceProviderID", referencedColumnName = "ID", nullable = false)
    public ServiceProviderEntity getServiceProviderByServiceProviderId() {
        return serviceProviderByServiceProviderId;
    }

    public void setServiceProviderByServiceProviderId(ServiceProviderEntity serviceProviderByServiceProviderId) {
        this.serviceProviderByServiceProviderId = serviceProviderByServiceProviderId;
    }

    @ManyToOne
    @JoinColumn(name = "Rating", referencedColumnName = "ID", nullable = false)
    public RatingEntity getRatingByRating() {
        return ratingByRating;
    }

    public void setRatingByRating(RatingEntity ratingByRating) {
        this.ratingByRating = ratingByRating;
    }
}
