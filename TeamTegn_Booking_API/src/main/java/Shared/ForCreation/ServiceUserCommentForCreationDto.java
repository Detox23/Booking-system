package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ServiceUserCommentForCreationDto {
    private Integer serviceUserId;
    @NotNull
    private String commentText;

    public Integer getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(Integer serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
