package Shared.ForCreation;

import javax.validation.constraints.NotNull;

public class AssignmentCommentForUpdateDto {
    @NotNull
    private int id;
    private Integer assignmentId;
    @NotNull
    private String commentText;

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
