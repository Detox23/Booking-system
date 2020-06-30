package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "Role_RolePermissions", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class RoleRolePermissionsEntity {
    private int roleId;
    private int rolePermissionId;
    private boolean rolePermissionValue;

    @Id
    @Column(name = "RoleID", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "RolePermissionID", nullable = false)
    public int getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(int rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    @Basic
    @Column(name = "RolePermissionValue", nullable = false)
    public boolean isRolePermissionValue() {
        return rolePermissionValue;
    }

    public void setRolePermissionValue(boolean rolePermissionValue) {
        this.rolePermissionValue = rolePermissionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRolePermissionsEntity that = (RoleRolePermissionsEntity) o;

        if (roleId != that.roleId) return false;
        if (rolePermissionId != that.rolePermissionId) return false;
        return rolePermissionValue == that.rolePermissionValue;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + rolePermissionId;
        result = 31 * result + (rolePermissionValue ? 1 : 0);
        return result;
    }
}
