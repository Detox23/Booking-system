package Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "PDFSignature_Link", schema = "dbo", catalog = "TeamTegn_BookingSystem_Devleopment")
public class PdfSignatureLinkEntity {
    private String linkId;
    private Timestamp lastClickedDate;
    private int clickedCount;
    private int assignmentId;
    private Integer serviceProviderId;

    @Basic
    @Column(name = "LinkID")
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @Basic
    @Column(name = "LastClickedDate")
    public Timestamp getLastClickedDate() {
        return lastClickedDate;
    }

    public void setLastClickedDate(Timestamp lastClickedDate) {
        this.lastClickedDate = lastClickedDate;
    }

    @Basic
    @Column(name = "ClickedCount")
    public int getClickedCount() {
        return clickedCount;
    }

    public void setClickedCount(int clickedCount) {
        this.clickedCount = clickedCount;
    }

    @Basic
    @Column(name = "AssignmentID")
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    @Basic
    @Column(name = "ServiceProviderID")
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

        if (clickedCount != that.clickedCount) return false;
        if (assignmentId != that.assignmentId) return false;
        if (linkId != null ? !linkId.equals(that.linkId) : that.linkId != null) return false;
        if (lastClickedDate != null ? !lastClickedDate.equals(that.lastClickedDate) : that.lastClickedDate != null)
            return false;
        if (serviceProviderId != null ? !serviceProviderId.equals(that.serviceProviderId) : that.serviceProviderId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = linkId != null ? linkId.hashCode() : 0;
        result = 31 * result + (lastClickedDate != null ? lastClickedDate.hashCode() : 0);
        result = 31 * result + clickedCount;
        result = 31 * result + assignmentId;
        result = 31 * result + (serviceProviderId != null ? serviceProviderId.hashCode() : 0);
        return result;
    }
}
