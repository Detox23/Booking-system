package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Assignment_ServiceProviderInitialNameList", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentServiceProviderInitialNameListEntity {
    private int assignmentId;
    private String serviceProviderInitialNameList;

    @Id
    @Column(name = "AssignmentID", nullable = false)
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Basic
    @Column(name = "ServiceProviderInitialNameList", nullable = true, length = 2147483647)
    public String getServiceProviderInitialNameList() {
        return serviceProviderInitialNameList;
    }

    public void setServiceProviderInitialNameList(String serviceProviderInitialNameList) {
        this.serviceProviderInitialNameList = serviceProviderInitialNameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentServiceProviderInitialNameListEntity that = (AssignmentServiceProviderInitialNameListEntity) o;

        if (assignmentId != that.assignmentId) return false;
        if (serviceProviderInitialNameList != null ? !serviceProviderInitialNameList.equals(that.serviceProviderInitialNameList) : that.serviceProviderInitialNameList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assignmentId;
        result = 31 * result + (serviceProviderInitialNameList != null ? serviceProviderInitialNameList.hashCode() : 0);
        return result;
    }
}
