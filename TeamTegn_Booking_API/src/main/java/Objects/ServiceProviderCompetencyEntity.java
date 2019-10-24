package Objects;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ServiceProviderCompetency", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderCompetencyEntity {
    private int id;
    private String competency;
    private int createdBy;
    private Timestamp createdDate;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Competency")
    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }

    @Basic
    @Column(name = "CreatedBy")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CreatedDate")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderCompetencyEntity that = (ServiceProviderCompetencyEntity) o;

        if (id != that.id) return false;
        if (createdBy != that.createdBy) return false;
        if (competency != null ? !competency.equals(that.competency) : that.competency != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (competency != null ? competency.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
