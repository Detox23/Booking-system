package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Account_EAN", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AccountEanEntity {
    private int id;
    private String eanNumber;
    private int accountId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Basic
    @Column(name = "AccountID", nullable = false)
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
        return eanNumber != null ? eanNumber.equals(that.eanNumber) : that.eanNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eanNumber != null ? eanNumber.hashCode() : 0);
        result = 31 * result + accountId;
        return result;
    }
}
