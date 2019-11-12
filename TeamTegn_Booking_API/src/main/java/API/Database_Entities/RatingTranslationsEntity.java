package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Rating_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class RatingTranslationsEntity {
    private int ratingId;
    private String ratingName;
    private String languageCulture;

    @Id
    @Column(name = "RatingID", nullable = false)
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
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
    @Column(name = "LanguageCulture", nullable = false, length = 50)
    public String getLanguageCulture() {
        return languageCulture;
    }

    public void setLanguageCulture(String languageCulture) {
        this.languageCulture = languageCulture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingTranslationsEntity that = (RatingTranslationsEntity) o;

        if (ratingId != that.ratingId) return false;
        if (ratingName != null ? !ratingName.equals(that.ratingName) : that.ratingName != null) return false;
        return languageCulture != null ? languageCulture.equals(that.languageCulture) : that.languageCulture == null;
    }

    @Override
    public int hashCode() {
        int result = ratingId;
        result = 31 * result + (ratingName != null ? ratingName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
