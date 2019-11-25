package API.Database_Entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SystemUser_Department", schema = "dbo")
public class SystemUserDepartmentEntity {
    private SystemUserEntity systemUserBySystemUserId;
    private DepartmentEntity departmentByDepartmentId;

    @ManyToOne
    @JoinColumn(name = "SystemUserID", referencedColumnName = "ID", nullable = false)
    public SystemUserEntity getSystemUserBySystemUserId() {
        return systemUserBySystemUserId;
    }

    public void setSystemUserBySystemUserId(SystemUserEntity systemUserBySystemUserId) {
        this.systemUserBySystemUserId = systemUserBySystemUserId;
    }

    @ManyToOne
    @JoinColumn(name = "DepartmentID", referencedColumnName = "ID", nullable = false)
    public DepartmentEntity getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(DepartmentEntity departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }
}
