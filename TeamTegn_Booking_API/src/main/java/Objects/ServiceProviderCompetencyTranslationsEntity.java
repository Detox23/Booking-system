package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceProviderCompetency_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderCompetencyTranslationsEntity {
    private int serviceProviderCompetencyId;
    private String competency;
    private String languageCulture;

    @Basic
    @Column(name = "ServiceProviderCompetencyID")
    public int getServiceProviderCompetencyId() {
        return serviceProviderCompetencyId;
    }

    public void setServiceProviderCompetencyId(int serviceProviderCompetencyId) {
        this.serviceProviderCompetencyId = serviceProviderCompetencyId;
    }

    @Basic
    @Column(name = "Competency")
    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
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

        ServiceProviderCompetencyTranslationsEntity that = (ServiceProviderCompetencyTranslationsEntity) o;

        if (serviceProviderCompetencyId != that.serviceProviderCompetencyId) return false;
        if (competency != null ? !competency.equals(that.competency) : that.competency != null) return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderCompetencyId;
        result = 31 * result + (competency != null ? competency.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
