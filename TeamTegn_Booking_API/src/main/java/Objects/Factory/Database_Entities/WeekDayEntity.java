package Objects.Factory.Database_Entities;

import javax.persistence.*;

@Entity
@Table(name = "WeekDay", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class WeekDayEntity {
    private int id;
    private String weekDay;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "WeekDay", nullable = true, length = 50)
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeekDayEntity that = (WeekDayEntity) o;

        if (id != that.id) return false;
        return weekDay != null ? weekDay.equals(that.weekDay) : that.weekDay == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        return result;
    }
}
