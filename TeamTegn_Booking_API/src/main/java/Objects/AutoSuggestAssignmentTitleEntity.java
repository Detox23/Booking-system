package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AutoSuggest_AssignmentTitle", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AutoSuggestAssignmentTitleEntity {
    private int serviceUserId;
    private String assignmentTitle;

    @Basic
    @Column(name = "ServiceUserID")
    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    @Basic
    @Column(name = "AssignmentTitle")
    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoSuggestAssignmentTitleEntity that = (AutoSuggestAssignmentTitleEntity) o;

        if (serviceUserId != that.serviceUserId) return false;
        if (assignmentTitle != null ? !assignmentTitle.equals(that.assignmentTitle) : that.assignmentTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceUserId;
        result = 31 * result + (assignmentTitle != null ? assignmentTitle.hashCode() : 0);
        return result;
    }
}
