package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SystemUser_Roles", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class SystemUserRolesEntity {
    private int systemUserId;
    private Integer roleId;

    @Basic
    @Column(name = "SystemUserID")
    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "RoleID")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemUserRolesEntity that = (SystemUserRolesEntity) o;

        if (systemUserId != that.systemUserId) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = systemUserId;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
