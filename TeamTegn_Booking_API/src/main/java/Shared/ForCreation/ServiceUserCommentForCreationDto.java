package Shared.ForCreation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ServiceUserCommentForCreationDto {
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp commentDate;
    private String commentText;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
