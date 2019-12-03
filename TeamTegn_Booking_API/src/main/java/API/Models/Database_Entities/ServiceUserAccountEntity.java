package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceUser_Account", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceUserAccountEntity {
    private int id;
    private int serviceUserId;
    private int accountId;

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

        if (id != that.id) return false;
        if (serviceUserId != that.serviceUserId) return false;
        if (accountId != that.accountId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceUserId;
        result = 31 * result + accountId;
        return result;
    }
}
