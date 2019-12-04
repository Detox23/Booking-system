package API.Models.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EmailLog", schema = "dbo")
public class EmailLogEntity {
    private int logId;
    private int systemUserId;
    private String objectType;
    private Integer objectId;
    private String objectEmail;
    private Timestamp sentDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID", nullable = false)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "SystemUserID", nullable = false)
    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "ObjectType", nullable = true, length = 250)
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Basic
    @Column(name = "ObjectID", nullable = true)
    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "ObjectEmail", nullable = true, length = 250)
    public String getObjectEmail() {
        return objectEmail;
    }

    public void setObjectEmail(String objectEmail) {
        this.objectEmail = objectEmail;
    }

    @Basic
    @Column(name = "SentDate", nullable = true)
    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailLogEntity that = (EmailLogEntity) o;

        if (logId != that.logId) return false;
        if (systemUserId != that.systemUserId) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (objectEmail != null ? !objectEmail.equals(that.objectEmail) : that.objectEmail != null) return false;
        if (sentDate != null ? !sentDate.equals(that.sentDate) : that.sentDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + systemUserId;
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (objectEmail != null ? objectEmail.hashCode() : 0);
        result = 31 * result + (sentDate != null ? sentDate.hashCode() : 0);
        return result;
    }
}
