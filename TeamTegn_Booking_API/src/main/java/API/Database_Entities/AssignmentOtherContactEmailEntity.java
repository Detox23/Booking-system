package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentOtherContactEmail", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentOtherContactEmailEntity {
    private int id;
    private String email;

    @Basic
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "Email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentOtherContactEmailEntity that = (AssignmentOtherContactEmailEntity) o;

        if (id != that.id) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
