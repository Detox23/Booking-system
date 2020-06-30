package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DateDimension", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class DateDimensionEntity {
    private int dateKey;
    private Date date;
    private byte day;
    private String daySuffix;
    private byte weekday;
    private String weekDayName;
    private boolean isWeekend;
    private boolean isHoliday;
    private String holidayText;
    private byte dowInMonth;
    private short dayOfYear;
    private byte weekOfMonth;
    private byte weekOfYear;
    private byte isoWeekOfYear;
    private byte month;
    private String monthName;
    private byte quarter;
    private String quarterName;
    private int year;
    private String mmyyyy;
    private String monthYear;
    private Date firstDayOfMonth;
    private Date lastDayOfMonth;
    private Date firstDayOfQuarter;
    private Date lastDayOfQuarter;
    private Date firstDayOfYear;
    private Date lastDayOfYear;
    private Date firstDayOfNextMonth;
    private Date firstDayOfNextYear;

    @Id
    @Column(name = "DateKey", nullable = false)
    public int getDateKey() {
        return dateKey;
    }

    public void setDateKey(int dateKey) {
        this.dateKey = dateKey;
    }

    @Basic
    @Column(name = "Date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Day", nullable = false)
    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    @Basic
    @Column(name = "DaySuffix", nullable = false, length = 2)
    public String getDaySuffix() {
        return daySuffix;
    }

    public void setDaySuffix(String daySuffix) {
        this.daySuffix = daySuffix;
    }

    @Basic
    @Column(name = "Weekday", nullable = false)
    public byte getWeekday() {
        return weekday;
    }

    public void setWeekday(byte weekday) {
        this.weekday = weekday;
    }

    @Basic
    @Column(name = "WeekDayName", nullable = false, length = 10)
    public String getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(String weekDayName) {
        this.weekDayName = weekDayName;
    }

    @Basic
    @Column(name = "IsWeekend", nullable = false)
    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    @Basic
    @Column(name = "IsHoliday", nullable = false)
    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    @Basic
    @Column(name = "HolidayText", nullable = true, length = 64)
    public String getHolidayText() {
        return holidayText;
    }

    public void setHolidayText(String holidayText) {
        this.holidayText = holidayText;
    }

    @Basic
    @Column(name = "DOWInMonth", nullable = false)
    public byte getDowInMonth() {
        return dowInMonth;
    }

    public void setDowInMonth(byte dowInMonth) {
        this.dowInMonth = dowInMonth;
    }

    @Basic
    @Column(name = "DayOfYear", nullable = false)
    public short getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(short dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    @Basic
    @Column(name = "WeekOfMonth", nullable = false)
    public byte getWeekOfMonth() {
        return weekOfMonth;
    }

    public void setWeekOfMonth(byte weekOfMonth) {
        this.weekOfMonth = weekOfMonth;
    }

    @Basic
    @Column(name = "WeekOfYear", nullable = false)
    public byte getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(byte weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    @Basic
    @Column(name = "ISOWeekOfYear", nullable = false)
    public byte getIsoWeekOfYear() {
        return isoWeekOfYear;
    }

    public void setIsoWeekOfYear(byte isoWeekOfYear) {
        this.isoWeekOfYear = isoWeekOfYear;
    }

    @Basic
    @Column(name = "Month", nullable = false)
    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    @Basic
    @Column(name = "MonthName", nullable = false, length = 10)
    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    @Basic
    @Column(name = "Quarter", nullable = false)
    public byte getQuarter() {
        return quarter;
    }

    public void setQuarter(byte quarter) {
        this.quarter = quarter;
    }

    @Basic
    @Column(name = "QuarterName", nullable = false, length = 6)
    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    @Basic
    @Column(name = "Year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "MMYYYY", nullable = false, length = 6)
    public String getMmyyyy() {
        return mmyyyy;
    }

    public void setMmyyyy(String mmyyyy) {
        this.mmyyyy = mmyyyy;
    }

    @Basic
    @Column(name = "MonthYear", nullable = false, length = 7)
    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    @Basic
    @Column(name = "FirstDayOfMonth", nullable = false)
    public Date getFirstDayOfMonth() {
        return firstDayOfMonth;
    }

    public void setFirstDayOfMonth(Date firstDayOfMonth) {
        this.firstDayOfMonth = firstDayOfMonth;
    }

    @Basic
    @Column(name = "LastDayOfMonth", nullable = false)
    public Date getLastDayOfMonth() {
        return lastDayOfMonth;
    }

    public void setLastDayOfMonth(Date lastDayOfMonth) {
        this.lastDayOfMonth = lastDayOfMonth;
    }

    @Basic
    @Column(name = "FirstDayOfQuarter", nullable = false)
    public Date getFirstDayOfQuarter() {
        return firstDayOfQuarter;
    }

    public void setFirstDayOfQuarter(Date firstDayOfQuarter) {
        this.firstDayOfQuarter = firstDayOfQuarter;
    }

    @Basic
    @Column(name = "LastDayOfQuarter", nullable = false)
    public Date getLastDayOfQuarter() {
        return lastDayOfQuarter;
    }

    public void setLastDayOfQuarter(Date lastDayOfQuarter) {
        this.lastDayOfQuarter = lastDayOfQuarter;
    }

    @Basic
    @Column(name = "FirstDayOfYear", nullable = false)
    public Date getFirstDayOfYear() {
        return firstDayOfYear;
    }

    public void setFirstDayOfYear(Date firstDayOfYear) {
        this.firstDayOfYear = firstDayOfYear;
    }

    @Basic
    @Column(name = "LastDayOfYear", nullable = false)
    public Date getLastDayOfYear() {
        return lastDayOfYear;
    }

    public void setLastDayOfYear(Date lastDayOfYear) {
        this.lastDayOfYear = lastDayOfYear;
    }

    @Basic
    @Column(name = "FirstDayOfNextMonth", nullable = false)
    public Date getFirstDayOfNextMonth() {
        return firstDayOfNextMonth;
    }

    public void setFirstDayOfNextMonth(Date firstDayOfNextMonth) {
        this.firstDayOfNextMonth = firstDayOfNextMonth;
    }

    @Basic
    @Column(name = "FirstDayOfNextYear", nullable = false)
    public Date getFirstDayOfNextYear() {
        return firstDayOfNextYear;
    }

    public void setFirstDayOfNextYear(Date firstDayOfNextYear) {
        this.firstDayOfNextYear = firstDayOfNextYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateDimensionEntity that = (DateDimensionEntity) o;

        if (dateKey != that.dateKey) return false;
        if (day != that.day) return false;
        if (weekday != that.weekday) return false;
        if (isWeekend != that.isWeekend) return false;
        if (isHoliday != that.isHoliday) return false;
        if (dowInMonth != that.dowInMonth) return false;
        if (dayOfYear != that.dayOfYear) return false;
        if (weekOfMonth != that.weekOfMonth) return false;
        if (weekOfYear != that.weekOfYear) return false;
        if (isoWeekOfYear != that.isoWeekOfYear) return false;
        if (month != that.month) return false;
        if (quarter != that.quarter) return false;
        if (year != that.year) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (daySuffix != null ? !daySuffix.equals(that.daySuffix) : that.daySuffix != null) return false;
        if (weekDayName != null ? !weekDayName.equals(that.weekDayName) : that.weekDayName != null) return false;
        if (holidayText != null ? !holidayText.equals(that.holidayText) : that.holidayText != null) return false;
        if (monthName != null ? !monthName.equals(that.monthName) : that.monthName != null) return false;
        if (quarterName != null ? !quarterName.equals(that.quarterName) : that.quarterName != null) return false;
        if (mmyyyy != null ? !mmyyyy.equals(that.mmyyyy) : that.mmyyyy != null) return false;
        if (monthYear != null ? !monthYear.equals(that.monthYear) : that.monthYear != null) return false;
        if (firstDayOfMonth != null ? !firstDayOfMonth.equals(that.firstDayOfMonth) : that.firstDayOfMonth != null)
            return false;
        if (lastDayOfMonth != null ? !lastDayOfMonth.equals(that.lastDayOfMonth) : that.lastDayOfMonth != null)
            return false;
        if (firstDayOfQuarter != null ? !firstDayOfQuarter.equals(that.firstDayOfQuarter) : that.firstDayOfQuarter != null)
            return false;
        if (lastDayOfQuarter != null ? !lastDayOfQuarter.equals(that.lastDayOfQuarter) : that.lastDayOfQuarter != null)
            return false;
        if (firstDayOfYear != null ? !firstDayOfYear.equals(that.firstDayOfYear) : that.firstDayOfYear != null)
            return false;
        if (lastDayOfYear != null ? !lastDayOfYear.equals(that.lastDayOfYear) : that.lastDayOfYear != null)
            return false;
        if (firstDayOfNextMonth != null ? !firstDayOfNextMonth.equals(that.firstDayOfNextMonth) : that.firstDayOfNextMonth != null)
            return false;
        return firstDayOfNextYear != null ? firstDayOfNextYear.equals(that.firstDayOfNextYear) : that.firstDayOfNextYear == null;
    }

    @Override
    public int hashCode() {
        int result = dateKey;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) day;
        result = 31 * result + (daySuffix != null ? daySuffix.hashCode() : 0);
        result = 31 * result + (int) weekday;
        result = 31 * result + (weekDayName != null ? weekDayName.hashCode() : 0);
        result = 31 * result + (isWeekend ? 1 : 0);
        result = 31 * result + (isHoliday ? 1 : 0);
        result = 31 * result + (holidayText != null ? holidayText.hashCode() : 0);
        result = 31 * result + (int) dowInMonth;
        result = 31 * result + (int) dayOfYear;
        result = 31 * result + (int) weekOfMonth;
        result = 31 * result + (int) weekOfYear;
        result = 31 * result + (int) isoWeekOfYear;
        result = 31 * result + (int) month;
        result = 31 * result + (monthName != null ? monthName.hashCode() : 0);
        result = 31 * result + (int) quarter;
        result = 31 * result + (quarterName != null ? quarterName.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (mmyyyy != null ? mmyyyy.hashCode() : 0);
        result = 31 * result + (monthYear != null ? monthYear.hashCode() : 0);
        result = 31 * result + (firstDayOfMonth != null ? firstDayOfMonth.hashCode() : 0);
        result = 31 * result + (lastDayOfMonth != null ? lastDayOfMonth.hashCode() : 0);
        result = 31 * result + (firstDayOfQuarter != null ? firstDayOfQuarter.hashCode() : 0);
        result = 31 * result + (lastDayOfQuarter != null ? lastDayOfQuarter.hashCode() : 0);
        result = 31 * result + (firstDayOfYear != null ? firstDayOfYear.hashCode() : 0);
        result = 31 * result + (lastDayOfYear != null ? lastDayOfYear.hashCode() : 0);
        result = 31 * result + (firstDayOfNextMonth != null ? firstDayOfNextMonth.hashCode() : 0);
        result = 31 * result + (firstDayOfNextYear != null ? firstDayOfNextYear.hashCode() : 0);
        return result;
    }
}
