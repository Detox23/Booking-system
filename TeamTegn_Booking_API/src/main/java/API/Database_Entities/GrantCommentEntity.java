package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Grant_Comment", schema = "dbo")
public class GrantCommentEntity {
    private int id;
    private Integer userId;
    private Integer grantId;
    private Timestamp commentDate;
    private String commentText;

    @Id
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
    @Column(name = "GrantID", nullable = true)
    public Integer getGrantId() {
        return grantId;
    }

    public void setGrantId(Integer grantId) {
        this.grantId = grantId;
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

        GrantCommentEntity that = (GrantCommentEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (grantId != null ? !grantId.equals(that.grantId) : that.grantId != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        return commentText != null ? commentText.equals(that.commentText) : that.commentText == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (grantId != null ? grantId.hashCode() : 0);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        return result;
    }
}
