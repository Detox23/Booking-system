package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceUser_Account", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceUserAccountEntity {
    private int serviceUserId;
    private int accountId;

    @Id
    @Column(name = "ServiceUserID", nullable = false)
    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
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

        ServiceUserAccountEntity that = (ServiceUserAccountEntity) o;

        if (serviceUserId != that.serviceUserId) return false;
        return accountId == that.accountId;
    }

    @Override
    public int hashCode() {
        int result = serviceUserId;
        result = 31 * result + accountId;
        return result;
    }
}
