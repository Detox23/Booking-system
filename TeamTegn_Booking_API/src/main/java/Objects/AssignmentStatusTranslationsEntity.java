package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AssignmentStatus_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentStatusTranslationsEntity {
    private int assignmentStatusId;
    private String assignmentStatusName;
    private String languageCulture;

    @Basic
    @Column(name = "AssignmentStatusID")
    public int getAssignmentStatusId() {
        return assignmentStatusId;
    }

    public void setAssignmentStatusId(int assignmentStatusId) {
        this.assignmentStatusId = assignmentStatusId;
    }

    @Basic
    @Column(name = "AssignmentStatusName")
    public String getAssignmentStatusName() {
        return assignmentStatusName;
    }

    public void setAssignmentStatusName(String assignmentStatusName) {
        this.assignmentStatusName = assignmentStatusName;
    }

    @Basic
    @Column(name = "LanguageCulture")
    public String getLanguageCulture() {
        return languageCulture;
    }

    public void setLanguageCulture(String languageCulture) {
        this.languageCulture = languageCulture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentStatusTranslationsEntity that = (AssignmentStatusTranslationsEntity) o;

        if (assignmentStatusId != that.assignmentStatusId) return false;
        if (assignmentStatusName != null ? !assignmentStatusName.equals(that.assignmentStatusName) : that.assignmentStatusName != null)
            return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assignmentStatusId;
        result = 31 * result + (assignmentStatusName != null ? assignmentStatusName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
