package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Assignment_ServiceProvider", schema = "dbo")
public class AssignmentServiceProviderEntity {
    private int id;
    private int assignmentId;
    private int serviceProviderId;
    private String serviceProviderFirstName;
    private String serviceProviderMiddleName;
    private String serviceProviderLastName;
    private String serviceProviderInitials;

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
    @Column(name = "AssignmentID", nullable = false)
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Basic
    @Column(name = "ServiceProviderID", nullable = false)
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
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

        if (id != that.id) return false;
        if (assignmentId != that.assignmentId) return false;
        if (serviceProviderId != that.serviceProviderId) return false;
        if (serviceProviderFirstName != null ? !serviceProviderFirstName.equals(that.serviceProviderFirstName) : that.serviceProviderFirstName != null)
            return false;
        if (serviceProviderMiddleName != null ? !serviceProviderMiddleName.equals(that.serviceProviderMiddleName) : that.serviceProviderMiddleName != null)
            return false;
        if (serviceProviderLastName != null ? !serviceProviderLastName.equals(that.serviceProviderLastName) : that.serviceProviderLastName != null)
            return false;
        return serviceProviderInitials != null ? serviceProviderInitials.equals(that.serviceProviderInitials) : that.serviceProviderInitials == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + assignmentId;
        result = 31 * result + serviceProviderId;
        result = 31 * result + (serviceProviderFirstName != null ? serviceProviderFirstName.hashCode() : 0);
        result = 31 * result + (serviceProviderMiddleName != null ? serviceProviderMiddleName.hashCode() : 0);
        result = 31 * result + (serviceProviderLastName != null ? serviceProviderLastName.hashCode() : 0);
        result = 31 * result + (serviceProviderInitials != null ? serviceProviderInitials.hashCode() : 0);
        return result;
    }
}
