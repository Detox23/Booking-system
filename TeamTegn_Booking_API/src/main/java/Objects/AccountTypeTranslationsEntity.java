package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AccountType_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AccountTypeTranslationsEntity {
    private int accountTypeId;
    private String accountType;
    private String languageCulture;

    @Basic
    @Column(name = "AccountTypeID")
    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Basic
    @Column(name = "AccountType")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

        AccountTypeTranslationsEntity that = (AccountTypeTranslationsEntity) o;

        if (accountTypeId != that.accountTypeId) return false;
        if (accountType != null ? !accountType.equals(that.accountType) : that.accountType != null) return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountTypeId;
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
