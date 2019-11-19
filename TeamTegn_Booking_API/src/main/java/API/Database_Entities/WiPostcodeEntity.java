package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "WI_Postcode", schema = "dbo")
public class WiPostcodeEntity {
    private String postcode;
    private String city;
    private Boolean copenhagen;
    private Boolean fredericia;
    private Boolean arhus;

    @Id
    @Column(name = "Postcode", nullable = false, length = 50)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "City", nullable = false, length = 250)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Copenhagen", nullable = true)
    public Boolean getCopenhagen() {
        return copenhagen;
    }

    public void setCopenhagen(Boolean copenhagen) {
        this.copenhagen = copenhagen;
    }

    @Basic
    @Column(name = "Fredericia", nullable = true)
    public Boolean getFredericia() {
        return fredericia;
    }

    public void setFredericia(Boolean fredericia) {
        this.fredericia = fredericia;
    }

    @Basic
    @Column(name = "Arhus", nullable = true)
    public Boolean getArhus() {
        return arhus;
    }

    public void setArhus(Boolean arhus) {
        this.arhus = arhus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WiPostcodeEntity that = (WiPostcodeEntity) o;

        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (copenhagen != null ? !copenhagen.equals(that.copenhagen) : that.copenhagen != null) return false;
        if (fredericia != null ? !fredericia.equals(that.fredericia) : that.fredericia != null) return false;
        return arhus != null ? arhus.equals(that.arhus) : that.arhus == null;
    }

    @Override
    public int hashCode() {
        int result = postcode != null ? postcode.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (copenhagen != null ? copenhagen.hashCode() : 0);
        result = 31 * result + (fredericia != null ? fredericia.hashCode() : 0);
        result = 31 * result + (arhus != null ? arhus.hashCode() : 0);
        return result;
    }
}
