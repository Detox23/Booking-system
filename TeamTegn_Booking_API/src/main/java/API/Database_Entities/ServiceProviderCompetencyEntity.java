package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "ServiceProviderCompetency", schema = "dbo")
public class ServiceProviderCompetencyEntity {
    private Integer id;
    private String competency;
    private Integer createdBy;
    private Timestamp createdDate;
    private Boolean isDeleted;
    private Collection<ServiceProviderServiceProviderCompetencyEntity> serviceProviderServiceProviderCompetenciesById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "CreatedBy", nullable = false)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CreatedDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

        ServiceProviderCompetencyEntity that = (ServiceProviderCompetencyEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (competency != null ? !competency.equals(that.competency) : that.competency != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (competency != null ? competency.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "serviceProviderCompetencyByCompetencyId")
    public Collection<ServiceProviderServiceProviderCompetencyEntity> getServiceProviderServiceProviderCompetenciesById() {
        return serviceProviderServiceProviderCompetenciesById;
    }

    public void setServiceProviderServiceProviderCompetenciesById(Collection<ServiceProviderServiceProviderCompetencyEntity> serviceProviderServiceProviderCompetenciesById) {
        this.serviceProviderServiceProviderCompetenciesById = serviceProviderServiceProviderCompetenciesById;
    }
}
