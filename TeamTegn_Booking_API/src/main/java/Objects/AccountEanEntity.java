package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Account_EAN", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AccountEanEntity {
    private int id;
    private String eanNumber;
    private int accountId;

    @Basic
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
    @Column(name = "AccountID")
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEanEntity that = (AccountEanEntity) o;

        if (id != that.id) return false;
        if (accountId != that.accountId) return false;
        if (eanNumber != null ? !eanNumber.equals(that.eanNumber) : that.eanNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eanNumber != null ? eanNumber.hashCode() : 0);
        result = 31 * result + accountId;
        return result;
    }
}
