package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Assignment_ServiceProvider", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentServiceProviderEntity {
    private int serviceProviderId;
    private String serviceProviderFirstName;
    private String serviceProviderMiddleName;
    private String serviceProviderLastName;
    private String serviceProviderInitials;

    @Basic
    @Column(name = "ServiceProviderID")
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @Column(name = "ServiceProviderFirstName")
    public String getServiceProviderFirstName() {
        return serviceProviderFirstName;
    }

    public void setServiceProviderFirstName(String serviceProviderFirstName) {
        this.serviceProviderFirstName = serviceProviderFirstName;
    }

    @Basic
    @Column(name = "ServiceProviderMiddleName")
    public String getServiceProviderMiddleName() {
        return serviceProviderMiddleName;
    }

    public void setServiceProviderMiddleName(String serviceProviderMiddleName) {
        this.serviceProviderMiddleName = serviceProviderMiddleName;
    }

    @Basic
    @Column(name = "ServiceProviderLastName")
    public String getServiceProviderLastName() {
        return serviceProviderLastName;
    }

    public void setServiceProviderLastName(String serviceProviderLastName) {
        this.serviceProviderLastName = serviceProviderLastName;
    }

    @Basic
    @Column(name = "ServiceProviderInitials")
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

        if (serviceProviderId != that.serviceProviderId) return false;
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
        int result = serviceProviderId;
        result = 31 * result + (serviceProviderFirstName != null ? serviceProviderFirstName.hashCode() : 0);
        result = 31 * result + (serviceProviderMiddleName != null ? serviceProviderMiddleName.hashCode() : 0);
        result = 31 * result + (serviceProviderLastName != null ? serviceProviderLastName.hashCode() : 0);
        result = 31 * result + (serviceProviderInitials != null ? serviceProviderInitials.hashCode() : 0);
        return result;
    }
}
