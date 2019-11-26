package API.Test;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentTypeEntity {
    private Integer id;
    private String assignmentTypeName;
    private Boolean isDeleted;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentTypeName", nullable = true, length = 50)
    public String getAssignmentTypeName() {
        return assignmentTypeName;
    }

    public void setAssignmentTypeName(String assignmentTypeName) {
        this.assignmentTypeName = assignmentTypeName;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentTypeEntity that = (AssignmentTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (assignmentTypeName != null ? !assignmentTypeName.equals(that.assignmentTypeName) : that.assignmentTypeName != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assignmentTypeName != null ? assignmentTypeName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
