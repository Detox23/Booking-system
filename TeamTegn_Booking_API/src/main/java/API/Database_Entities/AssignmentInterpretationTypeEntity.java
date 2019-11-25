package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AssignmentInterpretationType", schema = "dbo")
public class AssignmentInterpretationTypeEntity {
    private Integer id;
    private String interpretationTypeName;
    private Boolean isDeleted;
    private Collection<AssignmentEntity> assignmentsById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "InterpretationTypeName", nullable = true, length = 250)
    public String getInterpretationTypeName() {
        return interpretationTypeName;
    }

    public void setInterpretationTypeName(String interpretationTypeName) {
        this.interpretationTypeName = interpretationTypeName;
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

        AssignmentInterpretationTypeEntity that = (AssignmentInterpretationTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (interpretationTypeName != null ? !interpretationTypeName.equals(that.interpretationTypeName) : that.interpretationTypeName != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (interpretationTypeName != null ? interpretationTypeName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "assignmentInterpretationTypeByInterpretationTypeId")
    public Collection<AssignmentEntity> getAssignmentsById() {
        return assignmentsById;
    }

    public void setAssignmentsById(Collection<AssignmentEntity> assignmentsById) {
        this.assignmentsById = assignmentsById;
    }
}
