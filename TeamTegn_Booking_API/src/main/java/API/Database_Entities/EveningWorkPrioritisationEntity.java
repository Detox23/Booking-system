package API.Database_Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "EveningWorkPrioritisation", schema = "dbo")
public class EveningWorkPrioritisationEntity {
    private Integer id;
    private String prioritisation;
    private Boolean isDeleted;
    private Collection<ServiceProviderEveningWorkEntity> serviceProviderEveningWorksById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        EveningWorkPrioritisationEntity that = (EveningWorkPrioritisationEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (prioritisation != null ? !prioritisation.equals(that.prioritisation) : that.prioritisation != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (prioritisation != null ? prioritisation.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eveningWorkPrioritisationByEveningWorkPrioritisationId")
    public Collection<ServiceProviderEveningWorkEntity> getServiceProviderEveningWorksById() {
        return serviceProviderEveningWorksById;
    }

    public void setServiceProviderEveningWorksById(Collection<ServiceProviderEveningWorkEntity> serviceProviderEveningWorksById) {
        this.serviceProviderEveningWorksById = serviceProviderEveningWorksById;
    }
}
