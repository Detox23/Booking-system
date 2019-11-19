package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Account_Comment", schema = "dbo")
public class AccountCommentEntity {
    private int id;
    private Integer userId;
    private Integer accountId;
    private Timestamp commentDate;
    private String commentText;

    @Id
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
    @Column(name = "AccountID", nullable = true)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

        AccountCommentEntity that = (AccountCommentEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        return commentText != null ? commentText.equals(that.commentText) : that.commentText == null;
    }

}
