package Objects;

import javax.persistence.*;

@Entity
@Table(name = "AutoSuggest_AssignmentPlace", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AutoSuggestAssignmentPlaceEntity {
    private int id;
    private String assignmentPlace;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AssignmentPlace")
    public String getAssignmentPlace() {
        return assignmentPlace;
    }

    public void setAssignmentPlace(String assignmentPlace) {
        this.assignmentPlace = assignmentPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoSuggestAssignmentPlaceEntity that = (AutoSuggestAssignmentPlaceEntity) o;

        if (id != that.id) return false;
        if (assignmentPlace != null ? !assignmentPlace.equals(that.assignmentPlace) : that.assignmentPlace != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assignmentPlace != null ? assignmentPlace.hashCode() : 0);
        return result;
    }
}
