package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ServiceUser_Comment", schema = "dbo")
public class ServiceUserCommentEntity {
    private Integer id;
    private Timestamp commentDate;
    private String commentText;
    private SystemUserEntity systemUserByUserId;
    private ServiceUserEntity serviceUserByServiceUserId;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        ServiceUserCommentEntity that = (ServiceUserCommentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        if (commentText != null ? !commentText.equals(that.commentText) : that.commentText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    public SystemUserEntity getSystemUserByUserId() {
        return systemUserByUserId;
    }

    public void setSystemUserByUserId(SystemUserEntity systemUserByUserId) {
        this.systemUserByUserId = systemUserByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "ServiceUserID", referencedColumnName = "ID")
    public ServiceUserEntity getServiceUserByServiceUserId() {
        return serviceUserByServiceUserId;
    }

    public void setServiceUserByServiceUserId(ServiceUserEntity serviceUserByServiceUserId) {
        this.serviceUserByServiceUserId = serviceUserByServiceUserId;
    }
}
