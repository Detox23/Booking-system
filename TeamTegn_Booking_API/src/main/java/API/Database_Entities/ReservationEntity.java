package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reservation", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ReservationEntity {
    private int id;
    private int serviceProviderId;
    private int assignmentId;
    private Boolean isClosed;
    private int closedBy;
    private Timestamp createdDate;
    private boolean isDeleted;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "AssignmentID", nullable = false)
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Basic
    @Column(name = "IsClosed", nullable = true)
    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    @Basic
    @Column(name = "ClosedBy", nullable = false)
    public int getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(int closedBy) {
        this.closedBy = closedBy;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (id != that.id) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (assignmentId != that.assignmentId) return false;
        if (closedBy != that.closedBy) return false;
        if (isDeleted != that.isDeleted) return false;
        if (isClosed != null ? !isClosed.equals(that.isClosed) : that.isClosed != null) return false;
        return createdDate != null ? createdDate.equals(that.createdDate) : that.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceProviderId;
        result = 31 * result + assignmentId;
        result = 31 * result + (isClosed != null ? isClosed.hashCode() : 0);
        result = 31 * result + closedBy;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
