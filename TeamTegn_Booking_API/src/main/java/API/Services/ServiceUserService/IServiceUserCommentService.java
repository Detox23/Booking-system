package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;

import java.util.List;

public interface IServiceUserCommentService {
    ServiceUserCommentDto add(int serviceUserId, ServiceUserCommentForCreationDto serviceProviderComment);

    ServiceUserCommentDto update(int id, ServiceUserCommentForUpdateDto serviceProviderComment);

    ServiceUserCommentDto find(int id, int commentID);

    boolean delete(int id, int commentID);

    List<ServiceUserCommentDto> findServiceUserComments(int id);
}
