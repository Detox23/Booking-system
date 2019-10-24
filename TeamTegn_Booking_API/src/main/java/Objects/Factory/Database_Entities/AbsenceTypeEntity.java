package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AbsenceType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AbsenceTypeEntity {
    private int id;
    private String absenceTypeName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AbsenceTypeName", nullable = false, length = 255)
    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbsenceTypeEntity that = (AbsenceTypeEntity) o;

        if (id != that.id) return false;
        if (absenceTypeName != null ? !absenceTypeName.equals(that.absenceTypeName) : that.absenceTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (absenceTypeName != null ? absenceTypeName.hashCode() : 0);
        return result;
    }
}
