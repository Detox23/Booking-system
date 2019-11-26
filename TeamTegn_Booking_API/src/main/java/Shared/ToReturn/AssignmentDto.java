package Shared.ToReturn;

import API.Database_Entities.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public class AssignmentDto {
    private Integer id;
    private String assignmentDescription;
    private String destinationStreet;
    private String destinationCity;
    private String destinationPostCode;
    private String destinationStateRegion;
    private String destinationCountry;
    private Timestamp assignmentDate;
    private Date assignmentEndDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer totalTime;
    private Integer createdBy;
    private Timestamp createdDate;
    private Timestamp lastModified;
    private Integer lastModifiedBy;
    private Boolean isDeleted;
    private Boolean assignedStatus;
    private Integer assignmentStatusId;
    private String bookingNumber;
    private String serviceUserAccountEan;
    private Integer recurrenceType;
    private Integer recurrencyFrequency;
    private String otherContactEmail;
    private String dntmid;
    private String zone;
    private String moreInfo;
    private String ktstid;
    private String ktstParentId;
    private String recurrenceKey;
    private String assignmentPlace;
    private String stukComment;
    private Boolean isResale;
    private Integer resaleParentId;
    private AssignmentTypeDto assignmentTypeByAssignmentTypeId;
    private AssignmentImportanceDto assignmentImportanceByImportanceId;
    private AssignmentInterpretationTypeDto assignmentInterpretationTypeByInterpretationTypeId;
    private AssignmentTitleDto assignmentTitleByAssignmentTitle;
    private ServiceUserDto serviceUserByServiceUserId;
    private AccountDto accountByServiceUserAccountId;
    private AssignmentStatusEDto assignmentStatusByAssignmentStatusTypeId;
    private VocalLanguagesDto vocalLanguagesByVocalLanguageId;
    private Collection<AssignmentAssignmentStatusTypeDto> assignmentAssignmentStatusTypesById;
    private Collection<AssignmentCommentEntityDto> assignmentCommentsById;
    private Collection<Assignment_StukYearCodeEntityDto> assignmentStukYearCodesById;
    private Collection<AssignmentServiceProviderEntityDto> assignmentServiceProvidersById;
    private Collection<PdfSignatureLinkEntityDto> pdfSignatureLinksById;

}


