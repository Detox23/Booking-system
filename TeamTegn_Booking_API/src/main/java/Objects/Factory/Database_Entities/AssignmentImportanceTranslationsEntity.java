package Objects.Factory.Database_Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AssignmentImportance_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentImportanceTranslationsEntity {
    private int assignmentImportanceId;
    private String importanceName;
    private String languageCulture;

    @Basic
    @Column(name = "AssignmentImportanceID", nullable = false)
    public int getAssignmentImportanceId() {
        return assignmentImportanceId;
    }

    public void setAssignmentImportanceId(int assignmentImportanceId) {
        this.assignmentImportanceId = assignmentImportanceId;
    }

    @Basic
    @Column(name = "ImportanceName", nullable = false, length = 250)
    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
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

        AssignmentImportanceTranslationsEntity that = (AssignmentImportanceTranslationsEntity) o;

        if (assignmentImportanceId != that.assignmentImportanceId) return false;
        if (importanceName != null ? !importanceName.equals(that.importanceName) : that.importanceName != null)
            return false;
        return languageCulture != null ? languageCulture.equals(that.languageCulture) : that.languageCulture == null;
    }

    @Override
    public int hashCode() {
        int result = assignmentImportanceId;
        result = 31 * result + (importanceName != null ? importanceName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
