package API.Database_Entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceUser_Account", schema = "dbo")
public class ServiceUserAccountEntity {
    private ServiceUserEntity serviceUserByServiceUserId;
    private AccountEntity accountByAccountId;

    @ManyToOne
    @JoinColumn(name = "ServiceUserID", referencedColumnName = "ID", nullable = false)
    public ServiceUserEntity getServiceUserByServiceUserId() {
        return serviceUserByServiceUserId;
    }

    public void setServiceUserByServiceUserId(ServiceUserEntity serviceUserByServiceUserId) {
        this.serviceUserByServiceUserId = serviceUserByServiceUserId;
    }

    @ManyToOne
    @JoinColumn(name = "AccountID", referencedColumnName = "ID", nullable = false)
    public AccountEntity getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(AccountEntity accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }
}
