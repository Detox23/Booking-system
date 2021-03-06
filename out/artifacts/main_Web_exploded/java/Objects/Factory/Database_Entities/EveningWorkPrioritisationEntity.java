package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "EveningWorkPrioritisation", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class EveningWorkPrioritisationEntity {
    private int id;
    private String prioritisation;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EveningWorkPrioritisationEntity that = (EveningWorkPrioritisationEntity) o;

        if (id != that.id) return false;
        return prioritisation != null ? prioritisation.equals(that.prioritisation) : that.prioritisation == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (prioritisation != null ? prioritisation.hashCode() : 0);
        return result;
    }
}
