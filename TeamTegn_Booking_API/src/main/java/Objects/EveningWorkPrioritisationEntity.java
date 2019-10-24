package Objects;

import javax.persistence.*;

@Entity
@Table(name = "EveningWorkPrioritisation", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class EveningWorkPrioritisationEntity {
    private int id;
    private String prioritisation;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Prioritisation")
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
        if (prioritisation != null ? !prioritisation.equals(that.prioritisation) : that.prioritisation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (prioritisation != null ? prioritisation.hashCode() : 0);
        return result;
    }
}
