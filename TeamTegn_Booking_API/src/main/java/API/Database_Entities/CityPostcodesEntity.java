package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "CityPostcodes", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class CityPostcodesEntity {
    private int id;
    private String city;
    private String postcode;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "City", nullable = true, length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Postcode", nullable = true, length = 20)
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

        CityPostcodesEntity that = (CityPostcodesEntity) o;

        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }
}
