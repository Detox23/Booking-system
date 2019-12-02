package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AccountCommentForCreationDto {
    @NotNull
    private Integer accountId;
    @NotNull
    private String commentText;

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
