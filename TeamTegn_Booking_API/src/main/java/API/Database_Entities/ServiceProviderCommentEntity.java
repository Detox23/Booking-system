package API.Database_Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ServiceProvider_Comment", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class ServiceProviderCommentEntity {
    private int id;
    private int userId;
    private Integer serviceProviderId;
    private Timestamp commentDate;
    private String commentText;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @CreatedBy
    @Column(name = "UserID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ServiceProviderID")
    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "CommentDate")
    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Basic
    @Column(name = "CommentText", length = 2147483647)
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

        ServiceProviderCommentEntity that = (ServiceProviderCommentEntity) o;

        if (id != that.id) return false;
        if (serviceProviderId != null ? !serviceProviderId.equals(that.serviceProviderId) : that.serviceProviderId != null)
            return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        if (commentText != null ? !commentText.equals(that.commentText) : that.commentText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serviceProviderId != null ? serviceProviderId.hashCode() : 0);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        return result;
    }
}
