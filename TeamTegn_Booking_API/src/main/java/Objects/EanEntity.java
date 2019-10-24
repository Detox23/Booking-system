package Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EAN", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class EanEntity {
    private int id;
    private String eanNumber;
    private int createdBy;
    private Timestamp createdDate;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EANNumber")
    public String getEanNumber() {
        return eanNumber;
    }

    public void setEanNumber(String eanNumber) {
        this.eanNumber = eanNumber;
    }

    @Basic
    @Column(name = "CreatedBy")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CreatedDate")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EanEntity eanEntity = (EanEntity) o;

        if (id != eanEntity.id) return false;
        if (createdBy != eanEntity.createdBy) return false;
        if (eanNumber != null ? !eanNumber.equals(eanEntity.eanNumber) : eanEntity.eanNumber != null) return false;
        if (createdDate != null ? !createdDate.equals(eanEntity.createdDate) : eanEntity.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eanNumber != null ? eanNumber.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
