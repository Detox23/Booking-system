package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentSTUKYearCode", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentStukYearCodeEntity {
    private int id;
    private String stukYearCodeName;
    private boolean isDeleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        AssignmentStukYearCodeEntity that = (AssignmentStukYearCodeEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (stukYearCodeName != null ? !stukYearCodeName.equals(that.stukYearCodeName) : that.stukYearCodeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (stukYearCodeName != null ? stukYearCodeName.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
