package API.Models.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ZonesByPostNumber", schema = "dbo")
public class ZonesByPostNumberEntity {
    private int id;
    private String zone;
    private Integer postNumber;
    private Timestamp dateCreated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Zone", nullable = true, length = 50)
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Basic
    @Column(name = "PostNumber", nullable = true)
    public Integer getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(Integer postNumber) {
        this.postNumber = postNumber;
    }

    @Basic
    @Column(name = "DateCreated", nullable = true)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZonesByPostNumberEntity that = (ZonesByPostNumberEntity) o;

        if (id != that.id) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;
        if (postNumber != null ? !postNumber.equals(that.postNumber) : that.postNumber != null) return false;
        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (postNumber != null ? postNumber.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
