package Objects.Factory.Database_Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AssignmentType_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentTypeTranslationsEntity {
    private int assignmentTypeId;
    private String assignmentTypeName;
    private String languageCulture;

    @Basic
    @Column(name = "AssignmentTypeID", nullable = false)
    public int getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(int assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    @Basic
    @Column(name = "AssignmentTypeName", nullable = false, length = 50)
    public String getAssignmentTypeName() {
        return assignmentTypeName;
    }

    public void setAssignmentTypeName(String assignmentTypeName) {
        this.assignmentTypeName = assignmentTypeName;
    }

    @Basic
    @Column(name = "LanguageCulture", nullable = false, length = 50)
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

        AssignmentTypeTranslationsEntity that = (AssignmentTypeTranslationsEntity) o;

        if (assignmentTypeId != that.assignmentTypeId) return false;
        if (assignmentTypeName != null ? !assignmentTypeName.equals(that.assignmentTypeName) : that.assignmentTypeName != null)
            return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assignmentTypeId;
        result = 31 * result + (assignmentTypeName != null ? assignmentTypeName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
