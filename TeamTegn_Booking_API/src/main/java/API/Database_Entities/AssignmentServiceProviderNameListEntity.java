package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Assignment_ServiceProviderNameList", schema = "dbo")
public class AssignmentServiceProviderNameListEntity {
    private int assignmentId;
    private String serviceProviderNameList;

    @Id
    @Column(name = "AssignmentID", nullable = false)
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Basic
    @Column(name = "ServiceProviderNameList", nullable = true, length = 2147483647)
    public String getServiceProviderNameList() {
        return serviceProviderNameList;
    }

    public void setServiceProviderNameList(String serviceProviderNameList) {
        this.serviceProviderNameList = serviceProviderNameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentServiceProviderNameListEntity that = (AssignmentServiceProviderNameListEntity) o;

        if (assignmentId != that.assignmentId) return false;
        return serviceProviderNameList != null ? serviceProviderNameList.equals(that.serviceProviderNameList) : that.serviceProviderNameList == null;
    }

    @Override
    public int hashCode() {
        int result = assignmentId;
        result = 31 * result + (serviceProviderNameList != null ? serviceProviderNameList.hashCode() : 0);
        return result;
    }
}
