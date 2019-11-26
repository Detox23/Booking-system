package Shared.ToReturn;

import java.sql.Timestamp;

public class PdfSignatureLinkEntityDto {
    private String linkId;
    private Timestamp lastClickedDate;
    private Integer clickedCount;
    private Integer serviceProviderId;
}
