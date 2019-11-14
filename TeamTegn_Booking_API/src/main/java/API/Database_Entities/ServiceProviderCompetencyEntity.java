package API.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

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
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Competency", nullable = false, length = 100)
    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }

    @Basic
    @CreatedBy
    @Column(name = "CreatedBy", nullable = false)
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "CreatedDate", nullable = true)
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
        return createdDate != null ? createdDate.equals(that.createdDate) : that.createdDate == null;
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
