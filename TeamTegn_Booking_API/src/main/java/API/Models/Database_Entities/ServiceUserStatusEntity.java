package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "ServiceUserStatus", schema = "dbo")
public class ServiceUserStatusEntity {
    private int id;
    private String status;
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
    @Column(name = "Status", nullable = false, length = 100)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        ServiceUserStatusEntity that = (ServiceUserStatusEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
