package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AccountCommentForUpdateDto {
    private int id;
    @NotNull
    private Integer accountId;
    @NotNull
    private String commentText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
