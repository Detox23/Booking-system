package Objects;

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
    @Column(name = "AssignmentImportanceID")
    public int getAssignmentImportanceId() {
        return assignmentImportanceId;
    }

    public void setAssignmentImportanceId(int assignmentImportanceId) {
        this.assignmentImportanceId = assignmentImportanceId;
    }

    @Basic
    @Column(name = "ImportanceName")
    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
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

        AssignmentImportanceTranslationsEntity that = (AssignmentImportanceTranslationsEntity) o;

        if (assignmentImportanceId != that.assignmentImportanceId) return false;
        if (importanceName != null ? !importanceName.equals(that.importanceName) : that.importanceName != null)
            return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assignmentImportanceId;
        result = 31 * result + (importanceName != null ? importanceName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
