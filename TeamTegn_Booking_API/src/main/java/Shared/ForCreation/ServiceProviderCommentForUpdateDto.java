package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderCommentForUpdateDto {

    @NotNull
    private int id;
    @NotNull
    private Integer serviceProviderId;
    @NotNull
    private String commentText;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
