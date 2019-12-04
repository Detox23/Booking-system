package Shared.ToReturn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ServiceProviderCompetencyDto {
    private int id;
    private String competency;
    private int createdBy;
    @JsonFormat(timezone = "Europe/Rome", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }


    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
