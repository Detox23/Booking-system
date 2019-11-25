package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Assignment_ServiceProvider", schema = "dbo")
public class AssignmentServiceProviderEntity {
    private Integer serviceProviderId;
    private String serviceProviderFirstName;
    private String serviceProviderMiddleName;
    private String serviceProviderLastName;
    private String serviceProviderInitials;
    private AssignmentEntity assignmentByAssignmentId;

    @Basic
    @Column(name = "ServiceProviderID", nullable = false)
    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "ServiceProviderFirstName", nullable = true, length = 50)
    public String getServiceProviderFirstName() {
        return serviceProviderFirstName;
    }

    public void setServiceProviderFirstName(String serviceProviderFirstName) {
        this.serviceProviderFirstName = serviceProviderFirstName;
    }

    @Basic
    @Column(name = "ServiceProviderMiddleName", nullable = true, length = 50)
    public String getServiceProviderMiddleName() {
        return serviceProviderMiddleName;
    }

    public void setServiceProviderMiddleName(String serviceProviderMiddleName) {
        this.serviceProviderMiddleName = serviceProviderMiddleName;
    }

    @Basic
    @Column(name = "ServiceProviderLastName", nullable = true, length = 50)
    public String getServiceProviderLastName() {
        return serviceProviderLastName;
    }

    public void setServiceProviderLastName(String serviceProviderLastName) {
        this.serviceProviderLastName = serviceProviderLastName;
    }

    @Basic
    @Column(name = "ServiceProviderInitials", nullable = true, length = 20)
    public String getServiceProviderInitials() {
        return serviceProviderInitials;
    }

    public void setServiceProviderInitials(String serviceProviderInitials) {
        this.serviceProviderInitials = serviceProviderInitials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentServiceProviderEntity that = (AssignmentServiceProviderEntity) o;

        if (serviceProviderId != null ? !serviceProviderId.equals(that.serviceProviderId) : that.serviceProviderId != null)
            return false;
        if (serviceProviderFirstName != null ? !serviceProviderFirstName.equals(that.serviceProviderFirstName) : that.serviceProviderFirstName != null)
            return false;
        if (serviceProviderMiddleName != null ? !serviceProviderMiddleName.equals(that.serviceProviderMiddleName) : that.serviceProviderMiddleName != null)
            return false;
        if (serviceProviderLastName != null ? !serviceProviderLastName.equals(that.serviceProviderLastName) : that.serviceProviderLastName != null)
            return false;
        if (serviceProviderInitials != null ? !serviceProviderInitials.equals(that.serviceProviderInitials) : that.serviceProviderInitials != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceProviderId != null ? serviceProviderId.hashCode() : 0;
        result = 31 * result + (serviceProviderFirstName != null ? serviceProviderFirstName.hashCode() : 0);
        result = 31 * result + (serviceProviderMiddleName != null ? serviceProviderMiddleName.hashCode() : 0);
        result = 31 * result + (serviceProviderLastName != null ? serviceProviderLastName.hashCode() : 0);
        result = 31 * result + (serviceProviderInitials != null ? serviceProviderInitials.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentID", referencedColumnName = "ID", nullable = false)
    public AssignmentEntity getAssignmentByAssignmentId() {
        return assignmentByAssignmentId;
    }

    public void setAssignmentByAssignmentId(AssignmentEntity assignmentByAssignmentId) {
        this.assignmentByAssignmentId = assignmentByAssignmentId;
    }
}
