package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class ServiceProviderCommentForCreationDto {
    @NotNull
    private Integer serviceProviderId;
    @NotNull
    private String commentText;

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
