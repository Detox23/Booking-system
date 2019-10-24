package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AutoSuggest_AssignmentDescription", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AutoSuggestAssignmentDescriptionEntity {
    private int serviceUserId;
    private String assignmentDescription;

    @Basic
    @Column(name = "ServiceUserID")
    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    @Basic
    @Column(name = "AssignmentDescription")
    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoSuggestAssignmentDescriptionEntity that = (AutoSuggestAssignmentDescriptionEntity) o;

        if (serviceUserId != that.serviceUserId) return false;
        if (assignmentDescription != null ? !assignmentDescription.equals(that.assignmentDescription) : that.assignmentDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceUserId;
        result = 31 * result + (assignmentDescription != null ? assignmentDescription.hashCode() : 0);
        return result;
    }
}
