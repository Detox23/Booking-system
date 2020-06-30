package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentInterpretationType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentInterpretationTypeEntity {
    private int id;
    private String interpretationTypeName;

    @Id
    @Basic
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentInterpretationTypeEntity that = (AssignmentInterpretationTypeEntity) o;

        if (id != that.id) return false;
        return interpretationTypeName != null ? interpretationTypeName.equals(that.interpretationTypeName) : that.interpretationTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (interpretationTypeName != null ? interpretationTypeName.hashCode() : 0);
        return result;
    }
}
