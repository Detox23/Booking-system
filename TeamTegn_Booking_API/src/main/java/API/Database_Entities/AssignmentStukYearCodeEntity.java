package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AssignmentSTUKYearCode", schema = "dbo")
public class AssignmentStukYearCodeEntity {
    private Integer id;
    private String stukYearCodeName;
    private Boolean isDeleted;
    private Collection<Assignment_StukYearCodeEntity> assignmentStukYearCodesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STUKYearCodeName", nullable = true, length = 250)
    public String getStukYearCodeName() {
        return stukYearCodeName;
    }

    public void setStukYearCodeName(String stukYearCodeName) {
        this.stukYearCodeName = stukYearCodeName;
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

        AssignmentStukYearCodeEntity that = (AssignmentStukYearCodeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (stukYearCodeName != null ? !stukYearCodeName.equals(that.stukYearCodeName) : that.stukYearCodeName != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stukYearCodeName != null ? stukYearCodeName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "assignmentStukYearCodeByStukYearCodeId")
    public Collection<Assignment_StukYearCodeEntity> getAssignmentStukYearCodesById() {
        return assignmentStukYearCodesById;
    }

    public void setAssignmentStukYearCodesById(Collection<Assignment_StukYearCodeEntity> assignmentStukYearCodesById) {
        this.assignmentStukYearCodesById = assignmentStukYearCodesById;
    }
}
