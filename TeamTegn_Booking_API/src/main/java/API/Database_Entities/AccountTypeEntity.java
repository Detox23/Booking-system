package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AccountType", schema = "dbo")
public class AccountTypeEntity {
    private int id;
    private String accountType;
    private Boolean grantApplies;
    private Boolean isDeleted;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AccountType", nullable = false, length = 100)
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Basic
    @Column(name = "GrantApplies")
    public Boolean getGrantApplies() {
        return grantApplies;
    }

    public void setGrantApplies(Boolean grantApplies) {
        this.grantApplies = grantApplies;
    }

    @Basic
    @Column(name = "IsDeleted")
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @SuppressWarnings("EqualsReplaceableByObjectsCall")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountTypeEntity that = (AccountTypeEntity) o;

        if (id != that.id) return false;
        if (accountType != null ? !accountType.equals(that.accountType) : that.accountType != null) return false;
        if (grantApplies != null ? !grantApplies.equals(that.grantApplies) : that.grantApplies != null) return false;
        return isDeleted != null ? isDeleted.equals(that.isDeleted) : that.isDeleted == null;
    }

}
