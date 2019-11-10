package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "RolePermissions", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class RolePermissionsEntity {
    private int id;
    private String permissionName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PermissionName", nullable = true, length = 250)
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermissionsEntity that = (RolePermissionsEntity) o;

        if (id != that.id) return false;
        return permissionName != null ? permissionName.equals(that.permissionName) : that.permissionName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        return result;
    }
}
