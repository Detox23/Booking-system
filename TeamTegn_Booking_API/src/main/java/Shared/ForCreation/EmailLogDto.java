package Shared.ForCreation;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.sql.Timestamp;

public class EmailLogDto {
    private String objectType;
    private Integer objectId;
    private String objectEmail;


    public EmailLogDto(String objectType, Integer objectId, String objectEmail) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.objectEmail = objectEmail;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectEmail() {
        return objectEmail;
    }

    public void setObjectEmail(String objectEmail) {
        this.objectEmail = objectEmail;
    }
}
