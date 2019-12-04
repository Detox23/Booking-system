package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

public class ServiceProviderAbsenceForUpdateDto {
    @NotNull
    private int id;
    @NotNull
    private int serviceProviderId;
    @NotNull
    private Integer absenceTypeId;
    @NotNull
    private String absenceReason;
    @NotNull
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date fromDate;
    @NotNull
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time fromTime;
    @NotNull
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date toDate;
    @NotNull
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time toTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    public String getAbsenceReason() {
        return absenceReason;
    }

    public void setAbsenceReason(String absenceReason) {
        this.absenceReason = absenceReason;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

}
