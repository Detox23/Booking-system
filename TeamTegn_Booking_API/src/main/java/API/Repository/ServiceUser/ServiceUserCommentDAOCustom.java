package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserCommentEntity;
import Shared.ToReturn.ServiceUserCommentDto;

import java.util.List;

public interface ServiceUserCommentDAOCustom  {
    List<ServiceUserCommentDto> listServiceUserComments(int serviceUserID);
    ServiceUserCommentDto findServiceUserComment(int commentID);
    ServiceUserCommentDto addServiceUserComment(ServiceUserCommentEntity serviceProvider);
    ServiceUserCommentDto updateServiceUserComment(ServiceUserCommentEntity serviceProviderComment);
    boolean deleteServiceUserComment(int commentID);
}
