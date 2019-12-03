package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;

import java.util.List;

public interface IServiceUserCommentService {
    ServiceUserCommentDto addServiceUserComment(ServiceUserCommentForCreationDto serviceProviderComment);

    ServiceUserCommentDto updateServiceUserComment(ServiceUserCommentForUpdateDto serviceProviderComment);

    ServiceUserCommentDto findServiceUserComment(int commentID);

    boolean deleteServiceUserComment(int commentID);

    List<ServiceUserCommentDto> listServiceUserComments(int id);
}
