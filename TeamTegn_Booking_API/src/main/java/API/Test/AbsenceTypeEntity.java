package API.Test;

import javax.persistence.*;

@Entity
@Table(name = "AbsenceType", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AbsenceTypeEntity {
    private Integer id;
    private String absenceTypeName;
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
    @Column(name = "AbsenceTypeName", nullable = false, length = 255)
    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
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

        AbsenceTypeEntity that = (AbsenceTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (absenceTypeName != null ? !absenceTypeName.equals(that.absenceTypeName) : that.absenceTypeName != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (absenceTypeName != null ? absenceTypeName.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
