package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ExportSettings", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ExportSettingsEntity {
    private int id;
    private String keyName;
    private String valueName;
    private Timestamp created;
    private Boolean deleted;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "KeyName", nullable = false, length = 250)
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Basic
    @Column(name = "ValueName", nullable = true, length = 250)
    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @Basic
    @Column(name = "Created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "Deleted", nullable = true)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportSettingsEntity that = (ExportSettingsEntity) o;

        if (id != that.id) return false;
        if (keyName != null ? !keyName.equals(that.keyName) : that.keyName != null) return false;
        if (valueName != null ? !valueName.equals(that.valueName) : that.valueName != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        return deleted != null ? deleted.equals(that.deleted) : that.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (keyName != null ? keyName.hashCode() : 0);
        result = 31 * result + (valueName != null ? valueName.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
