package Objects;

import javax.persistence.*;

@Entity
@Table(name = "Rating", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class RatingEntity {
    private int id;
    private String ratingName;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RatingName")
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

        RatingEntity that = (RatingEntity) o;

        if (id != that.id) return false;
        if (ratingName != null ? !ratingName.equals(that.ratingName) : that.ratingName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ratingName != null ? ratingName.hashCode() : 0);
        return result;
    }
}
