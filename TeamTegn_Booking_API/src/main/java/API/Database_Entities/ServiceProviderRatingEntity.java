package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProviderRating", schema = "dbo")
public class ServiceProviderRatingEntity {
    private int id;
    private String ratingName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RatingName", nullable = false, length = 250)
    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderRatingEntity that = (ServiceProviderRatingEntity) o;

        if (id != that.id) return false;
        return ratingName != null ? ratingName.equals(that.ratingName) : that.ratingName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ratingName != null ? ratingName.hashCode() : 0);
        return result;
    }
}
