package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "WI_ServiceUser_MailLink", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class WiServiceUserMailLinkEntity {
    private String linkId;
    private int serviceUserId;
    private boolean isUsed;
    private Timestamp dateCreated;

    @Id
    @Column(name = "LinkID", nullable = false, length = 50)
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
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
    @Column(name = "IsUsed", nullable = false)
    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Basic
    @Column(name = "DateCreated", nullable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WiServiceUserMailLinkEntity that = (WiServiceUserMailLinkEntity) o;

        if (serviceUserId != that.serviceUserId) return false;
        if (isUsed != that.isUsed) return false;
        if (linkId != null ? !linkId.equals(that.linkId) : that.linkId != null) return false;
        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = linkId != null ? linkId.hashCode() : 0;
        result = 31 * result + serviceUserId;
        result = 31 * result + (isUsed ? 1 : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
