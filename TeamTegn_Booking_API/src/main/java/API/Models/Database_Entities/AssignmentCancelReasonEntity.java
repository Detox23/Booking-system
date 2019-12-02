package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentCancelReason", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentCancelReasonEntity {
    private int id;
    private String cancelReasonName;

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

        if (id != that.id) return false;
        if (cancelReasonName != null ? !cancelReasonName.equals(that.cancelReasonName) : that.cancelReasonName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cancelReasonName != null ? cancelReasonName.hashCode() : 0);
        return result;
    }
}
