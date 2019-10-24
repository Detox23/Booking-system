package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Rating_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class RatingTranslationsEntity {
    private int ratingId;
    private String ratingName;
    private String languageCulture;

    @Basic
    @Column(name = "RatingID")
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    @Basic
    @Column(name = "RatingName")
    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    @Basic
    @Column(name = "LanguageCulture")
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
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ratingId;
        result = 31 * result + (ratingName != null ? ratingName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
