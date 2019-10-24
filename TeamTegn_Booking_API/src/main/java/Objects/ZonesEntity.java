package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Zones", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ZonesEntity {
    private int zoneNumber;

    @Basic
    @Column(name = "ZoneNumber")
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

        if (zoneNumber != that.zoneNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return zoneNumber;
    }
}
