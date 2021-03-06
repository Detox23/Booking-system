package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "AssignmentImportance", schema = "dbo")
public class AssignmentImportanceEntity {
    private int id;
    private String importanceName;
    private boolean isDeleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ImportanceName", nullable = true, length = 250)
    public String getImportanceName() {
        return importanceName;
    }

    public void setImportanceName(String importanceName) {
        this.importanceName = importanceName;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false, columnDefinition = "bit default 0")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentImportanceEntity that = (AssignmentImportanceEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        return importanceName != null ? importanceName.equals(that.importanceName) : that.importanceName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (importanceName != null ? importanceName.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
