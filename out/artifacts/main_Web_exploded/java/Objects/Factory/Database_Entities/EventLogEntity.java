package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EventLog", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class EventLogEntity {
    private int id;
    private Integer systemUserId;
    private String exceptionMessage;
    private String stackTrace;
    private Timestamp dateCreated;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SystemUserID", nullable = true)
    public Integer getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "ExceptionMessage", nullable = true, length = 2147483647)
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Basic
    @Column(name = "StackTrace", nullable = true, length = 2147483647)
    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Basic
    @Column(name = "DateCreated", nullable = true)
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

        EventLogEntity that = (EventLogEntity) o;

        if (id != that.id) return false;
        if (systemUserId != null ? !systemUserId.equals(that.systemUserId) : that.systemUserId != null) return false;
        if (exceptionMessage != null ? !exceptionMessage.equals(that.exceptionMessage) : that.exceptionMessage != null)
            return false;
        if (stackTrace != null ? !stackTrace.equals(that.stackTrace) : that.stackTrace != null) return false;
        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (systemUserId != null ? systemUserId.hashCode() : 0);
        result = 31 * result + (exceptionMessage != null ? exceptionMessage.hashCode() : 0);
        result = 31 * result + (stackTrace != null ? stackTrace.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
