package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "EveningWorkPrioritisation", schema = "dbo")
public class EveningWorkPrioritisationEntity {
    private int id;
    private String prioritisation;
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
    @Column(name = "Prioritisation", nullable = true, length = 50)
    public String getPrioritisation() {
        return prioritisation;
    }

    public void setPrioritisation(String prioritisation) {
        this.prioritisation = prioritisation;
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

        EveningWorkPrioritisationEntity that = (EveningWorkPrioritisationEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (prioritisation != null ? !prioritisation.equals(that.prioritisation) : that.prioritisation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (prioritisation != null ? prioritisation.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
