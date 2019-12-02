package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ServiceUserCommentForUpdateDto {
    private int id;
    private Integer serviceUserId;
    private String commentText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
