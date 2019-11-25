package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Rating", schema = "dbo")
public class RatingEntity {
    private Integer id;
    private String ratingName;
    private Boolean isDeleted;
    private Collection<ServiceUserPreferencesEntity> serviceUserPreferencesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RatingName", nullable = false, length = 30)
    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
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

        RatingEntity that = (RatingEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ratingName != null ? !ratingName.equals(that.ratingName) : that.ratingName != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ratingName != null ? ratingName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "ratingByRating")
    public Collection<ServiceUserPreferencesEntity> getServiceUserPreferencesById() {
        return serviceUserPreferencesById;
    }

    public void setServiceUserPreferencesById(Collection<ServiceUserPreferencesEntity> serviceUserPreferencesById) {
        this.serviceUserPreferencesById = serviceUserPreferencesById;
    }
}
