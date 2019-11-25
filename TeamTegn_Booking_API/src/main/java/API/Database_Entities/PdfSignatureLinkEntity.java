package API.Database_Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PDFSignature_Link", schema = "dbo")
public class PdfSignatureLinkEntity {
    private String linkId;
    private Timestamp lastClickedDate;
    private Integer clickedCount;
    private Integer serviceProviderId;
    private AssignmentEntity assignmentByAssignmentId;

    @Basic
    @Column(name = "LinkID", nullable = false, length = 60)
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @Basic
    @Column(name = "LastClickedDate", nullable = false)
    public Timestamp getLastClickedDate() {
        return lastClickedDate;
    }

    public void setLastClickedDate(Timestamp lastClickedDate) {
        this.lastClickedDate = lastClickedDate;
    }

    @Basic
    @Column(name = "ClickedCount", nullable = false)
    public Integer getClickedCount() {
        return clickedCount;
    }

    public void setClickedCount(Integer clickedCount) {
        this.clickedCount = clickedCount;
    }

    @Basic
    @Column(name = "ServiceProviderID", nullable = true)
    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdfSignatureLinkEntity that = (PdfSignatureLinkEntity) o;

        if (linkId != null ? !linkId.equals(that.linkId) : that.linkId != null) return false;
        if (lastClickedDate != null ? !lastClickedDate.equals(that.lastClickedDate) : that.lastClickedDate != null)
            return false;
        if (clickedCount != null ? !clickedCount.equals(that.clickedCount) : that.clickedCount != null) return false;
        if (serviceProviderId != null ? !serviceProviderId.equals(that.serviceProviderId) : that.serviceProviderId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = linkId != null ? linkId.hashCode() : 0;
        result = 31 * result + (lastClickedDate != null ? lastClickedDate.hashCode() : 0);
        result = 31 * result + (clickedCount != null ? clickedCount.hashCode() : 0);
        result = 31 * result + (serviceProviderId != null ? serviceProviderId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "AssignmentID", referencedColumnName = "ID", nullable = false)
    public AssignmentEntity getAssignmentByAssignmentId() {
        return assignmentByAssignmentId;
    }

    public void setAssignmentByAssignmentId(AssignmentEntity assignmentByAssignmentId) {
        this.assignmentByAssignmentId = assignmentByAssignmentId;
    }
}
