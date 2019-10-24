package Objects.Factory.Database_Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AssignmentInterpretationType_Translations", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentInterpretationTypeTranslationsEntity {
    private int assignmentInterpretationTypeId;
    private String interpretationTypeName;
    private String languageCulture;

    @Basic
    @Column(name = "AssignmentInterpretationTypeID", nullable = false)
    public int getAssignmentInterpretationTypeId() {
        return assignmentInterpretationTypeId;
    }

    public void setAssignmentInterpretationTypeId(int assignmentInterpretationTypeId) {
        this.assignmentInterpretationTypeId = assignmentInterpretationTypeId;
    }

    @Basic
    @Column(name = "InterpretationTypeName", nullable = false, length = 250)
    public String getInterpretationTypeName() {
        return interpretationTypeName;
    }

    public void setInterpretationTypeName(String interpretationTypeName) {
        this.interpretationTypeName = interpretationTypeName;
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

        AssignmentInterpretationTypeTranslationsEntity that = (AssignmentInterpretationTypeTranslationsEntity) o;

        if (assignmentInterpretationTypeId != that.assignmentInterpretationTypeId) return false;
        if (interpretationTypeName != null ? !interpretationTypeName.equals(that.interpretationTypeName) : that.interpretationTypeName != null)
            return false;
        if (languageCulture != null ? !languageCulture.equals(that.languageCulture) : that.languageCulture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = assignmentInterpretationTypeId;
        result = 31 * result + (interpretationTypeName != null ? interpretationTypeName.hashCode() : 0);
        result = 31 * result + (languageCulture != null ? languageCulture.hashCode() : 0);
        return result;
    }
}
