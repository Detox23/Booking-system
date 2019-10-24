package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProviderOtherPreference", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderOtherPreferenceEntity {
    private int id;
    private String otherPreferenceName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "OtherPreferenceName", nullable = false, length = 255)
    public String getOtherPreferenceName() {
        return otherPreferenceName;
    }

    public void setOtherPreferenceName(String otherPreferenceName) {
        this.otherPreferenceName = otherPreferenceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderOtherPreferenceEntity that = (ServiceProviderOtherPreferenceEntity) o;

        if (id != that.id) return false;
        if (otherPreferenceName != null ? !otherPreferenceName.equals(that.otherPreferenceName) : that.otherPreferenceName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (otherPreferenceName != null ? otherPreferenceName.hashCode() : 0);
        return result;
    }
}
