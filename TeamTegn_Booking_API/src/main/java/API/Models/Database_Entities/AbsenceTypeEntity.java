package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AbsenceType", schema = "dbo")
public class AbsenceTypeEntity {
    private int id;
    private String absenceTypeName;
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
    @Column(name = "AbsenceTypeName", nullable = false, length = 255)
    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
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

        AbsenceTypeEntity that = (AbsenceTypeEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (absenceTypeName != null ? !absenceTypeName.equals(that.absenceTypeName) : that.absenceTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (absenceTypeName != null ? absenceTypeName.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
