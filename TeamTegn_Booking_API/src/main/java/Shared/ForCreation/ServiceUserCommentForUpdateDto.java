package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ServiceUserCommentForUpdateDto {

    private Integer userId;
    private Integer serviceUserId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp commentDate;
    private String commentText;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(Integer serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
