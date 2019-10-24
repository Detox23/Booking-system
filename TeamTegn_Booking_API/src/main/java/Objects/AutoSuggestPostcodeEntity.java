package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AutoSuggest_Postcode", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AutoSuggestPostcodeEntity {
    private int id;
    private String postcode;

    @Basic
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoSuggestPostcodeEntity that = (AutoSuggestPostcodeEntity) o;

        if (id != that.id) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }
}
