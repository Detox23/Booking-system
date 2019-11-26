package API.Test;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentCancelReason", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentCancelReasonEntity {
    private Integer id;
    private String cancelReasonName;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CancelReasonName", nullable = false, length = 200)
    public String getCancelReasonName() {
        return cancelReasonName;
    }

    public void setCancelReasonName(String cancelReasonName) {
        this.cancelReasonName = cancelReasonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentCancelReasonEntity that = (AssignmentCancelReasonEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cancelReasonName != null ? !cancelReasonName.equals(that.cancelReasonName) : that.cancelReasonName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cancelReasonName != null ? cancelReasonName.hashCode() : 0);
        return result;
    }
}
