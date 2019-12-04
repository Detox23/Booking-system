package Shared.ToReturn;

import java.sql.Timestamp;

public class PdfSignatureLinkEntityDto {
    private String linkId;
    private Timestamp lastClickedDate;
    private Integer clickedCount;
    private Integer serviceProviderId;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Timestamp getLastClickedDate() {
        return lastClickedDate;
    }

    public void setLastClickedDate(Timestamp lastClickedDate) {
        this.lastClickedDate = lastClickedDate;
    }

    public Integer getClickedCount() {
        return clickedCount;
    }

    public void setClickedCount(Integer clickedCount) {
        this.clickedCount = clickedCount;
    }

    public Integer getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }
}
