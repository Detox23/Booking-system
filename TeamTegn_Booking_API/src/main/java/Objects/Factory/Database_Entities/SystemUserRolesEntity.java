package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "SystemUser_Roles", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class SystemUserRolesEntity {
    private int systemUserId;
    private Integer roleId;

    @Id
    @Column(name = "SystemUserID", nullable = false)
    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "RoleID", nullable = true)
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
        return roleId != null ? roleId.equals(that.roleId) : that.roleId == null;
    }

    @Override
    public int hashCode() {
        int result = systemUserId;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
