package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderCommentForCreationDto;
import Shared.ForCreation.ServiceProviderCommentForUpdateDto;
import Shared.ToReturn.ServiceProviderCommentDto;

import java.util.List;

public interface IServiceProviderCommentService {
    ServiceProviderCommentDto addServiceProviderComment(ServiceProviderCommentForCreationDto serviceProviderComment);

    ServiceProviderCommentDto updateServiceProviderComment(ServiceProviderCommentForUpdateDto serviceProviderComment);

    ServiceProviderCommentDto findServiceProviderComment(int id, int commentID);

    boolean deleteServiceProviderComment(int id, int commentID);

    List<ServiceProviderCommentDto> findServiceProviderComments(int id);
}
