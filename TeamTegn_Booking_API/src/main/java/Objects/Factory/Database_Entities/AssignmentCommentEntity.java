package Objects.Factory.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Assignment_Comment", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class AssignmentCommentEntity {
    private int id;
    private Integer userId;
    private Timestamp commentDate;
    private String commentText;
    private AssignmentEntity assignmentByAssignmentId;

    @Basic
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CommentDate", nullable = true)
    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Basic
    @Column(name = "CommentText", nullable = true, length = 2147483647)
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignmentCommentEntity that = (AssignmentCommentEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        return commentText != null ? commentText.equals(that.commentText) : that.commentText == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentID", referencedColumnName = "ID")
    public AssignmentEntity getAssignmentByAssignmentId() {
        return assignmentByAssignmentId;
    }

    public void setAssignmentByAssignmentId(AssignmentEntity assignmentByAssignmentId) {
        this.assignmentByAssignmentId = assignmentByAssignmentId;
    }
}
