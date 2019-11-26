package API.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "SystemUser_Department", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class SystemUserDepartmentEntity {
    private int id;
    private int systemUserId;
    private int departmentId;

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

        if (id != that.id) return false;
        if (systemUserId != that.systemUserId) return false;
        if (departmentId != that.departmentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + systemUserId;
        result = 31 * result + departmentId;
        return result;
    }
}
