package API.Test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Assignment_AssignmentStatusType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentAssignmentStatusTypeEntity {
    private Integer id;
    private Timestamp dateCreated;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        AssignmentAssignmentStatusTypeEntity that = (AssignmentAssignmentStatusTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}