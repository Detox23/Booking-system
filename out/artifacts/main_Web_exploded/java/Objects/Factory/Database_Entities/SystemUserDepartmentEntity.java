package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "SystemUser_Department", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class SystemUserDepartmentEntity {
    private int systemUserId;
    private int departmentId;

    @Id
    @Column(name = "SystemUserID", nullable = false)
    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "DepartmentID", nullable = false)
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemUserDepartmentEntity that = (SystemUserDepartmentEntity) o;

        if (systemUserId != that.systemUserId) return false;
        return departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        int result = systemUserId;
        result = 31 * result + departmentId;
        return result;
    }
}
