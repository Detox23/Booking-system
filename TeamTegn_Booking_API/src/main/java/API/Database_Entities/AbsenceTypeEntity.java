package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AbsenceType", schema = "dbo")
public class AbsenceTypeEntity {
    private int id;
    private String absenceTypeName;

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
    @Column(name = "AbsenceTypeName", nullable = false)
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
        return absenceTypeName != null ? absenceTypeName.equals(that.absenceTypeName) : that.absenceTypeName == null;
    }

}
