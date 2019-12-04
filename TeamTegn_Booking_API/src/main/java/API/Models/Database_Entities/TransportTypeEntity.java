package API.Models.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "TransportType", schema = "dbo")
public class TransportTypeEntity {
    private int id;
    private String transport;
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
    @Column(name = "Transport", nullable = true, length = 50)
    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    @Basic
    @Column(name = "IsDeleted", nullable = false)
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

        TransportTypeEntity that = (TransportTypeEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (transport != null ? !transport.equals(that.transport) : that.transport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
