package Shared.ToReturn;

public class AssignmentServiceProviderDto {
    private int id;
    private int assignmentId;
    private int serviceProviderId;
    private String serviceProviderFirstName;
    private String serviceProviderMiddleName;
    private String serviceProviderLastName;
    private String serviceProviderInitials;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getServiceProviderFirstName() {
        return serviceProviderFirstName;
    }

    public void setServiceProviderFirstName(String serviceProviderFirstName) {
        this.serviceProviderFirstName = serviceProviderFirstName;
    }

    public String getServiceProviderMiddleName() {
        return serviceProviderMiddleName;
    }

    public void setServiceProviderMiddleName(String serviceProviderMiddleName) {
        this.serviceProviderMiddleName = serviceProviderMiddleName;
    }

    public String getServiceProviderLastName() {
        return serviceProviderLastName;
    }

    public void setServiceProviderLastName(String serviceProviderLastName) {
        this.serviceProviderLastName = serviceProviderLastName;
    }

    public String getServiceProviderInitials() {
        return serviceProviderInitials;
    }

    public void setServiceProviderInitials(String serviceProviderInitials) {
        this.serviceProviderInitials = serviceProviderInitials;
    }
}
