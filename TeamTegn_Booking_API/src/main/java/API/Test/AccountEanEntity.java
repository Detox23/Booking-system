package API.Test;

import javax.persistence.*;

@Entity
@Table(name = "Account_EAN", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AccountEanEntity {
    private Integer id;
    private String eanNumber;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EANNumber", nullable = true, length = 50)
    public String getEanNumber() {
        return eanNumber;
    }

    public void setEanNumber(String eanNumber) {
        this.eanNumber = eanNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEanEntity that = (AccountEanEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (eanNumber != null ? !eanNumber.equals(that.eanNumber) : that.eanNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eanNumber != null ? eanNumber.hashCode() : 0);
        return result;
    }
}
