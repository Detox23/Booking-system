package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceProviderRating_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderRatingTranslationsEntity {
    private int serviceProviderRatingId;
    private String ratingName;
    private String languageCulture;

    @Basic
    @Column(name = "ServiceProviderRatingID")
    public int getServiceProviderRatingId() {
        return serviceProviderRatingId;
    }

    public void setServiceProviderRatingId(int serviceProviderRatingId) {
        this.serviceProviderRatingId = serviceProviderRatingId;
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

        ServiceProviderRatingTranslationsEntity that = (ServiceProviderRatingTranslationsEntity) o;

        if (serviceProviderRatingId != that.serviceProviderRatingId) return false;
        if (ratingName != null ? !ratingName.equals(that.ratingName) : that.ratingName != null) return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderRatingId;
        result = 31 * result + (ratingName != null ? ratingName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
