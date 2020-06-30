package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentInterpretationType", schema = "dbo")
public class AssignmentInterpretationTypeEntity {
    private int id;
    private String interpretationTypeName;
    private boolean isDeleted;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "InterpretationTypeName", length = 250)
    public String getInterpretationTypeName() {
        return interpretationTypeName;
    }

    public void setInterpretationTypeName(String interpretationTypeName) {
        this.interpretationTypeName = interpretationTypeName;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false, columnDefinition = "bit default 0")
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

        AssignmentInterpretationTypeEntity that = (AssignmentInterpretationTypeEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        return interpretationTypeName != null ? interpretationTypeName.equals(that.interpretationTypeName) : that.interpretationTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (interpretationTypeName != null ? interpretationTypeName.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
