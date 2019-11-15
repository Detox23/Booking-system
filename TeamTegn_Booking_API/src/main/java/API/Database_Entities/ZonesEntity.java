package API.Database_Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Zones", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ZonesEntity {
    private int zoneNumber;

    @Id
    @Column(name = "ZoneNumber", nullable = false)
    public int getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(int zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZonesEntity that = (ZonesEntity) o;

        return zoneNumber == that.zoneNumber;
    }

    @Override
    public int hashCode() {
        return zoneNumber;
    }
}
