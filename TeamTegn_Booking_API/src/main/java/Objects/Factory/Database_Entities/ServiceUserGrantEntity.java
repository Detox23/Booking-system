package Objects.Factory.Database_Entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceUser_Grant", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceUserGrantEntity {
    private ServiceUserEntity serviceUserByServiceUserId;
    private GrantEntity grantByGrantId;

    @ManyToOne
    @JoinColumn(name = "ServiceUserID", referencedColumnName = "ID", nullable = false)
    public ServiceUserEntity getServiceUserByServiceUserId() {
        return serviceUserByServiceUserId;
    }

    public void setServiceUserByServiceUserId(ServiceUserEntity serviceUserByServiceUserId) {
        this.serviceUserByServiceUserId = serviceUserByServiceUserId;
    }

    @ManyToOne
    @JoinColumn(name = "GrantID", referencedColumnName = "ID", nullable = false)
    public GrantEntity getGrantByGrantId() {
        return grantByGrantId;
    }

    public void setGrantByGrantId(GrantEntity grantByGrantId) {
        this.grantByGrantId = grantByGrantId;
    }
}
