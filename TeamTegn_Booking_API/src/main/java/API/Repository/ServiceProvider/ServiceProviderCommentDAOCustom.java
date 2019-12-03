package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderCommentEntity;
import Shared.ToReturn.ServiceProviderCommentDto;

import java.util.List;

public interface ServiceProviderCommentDAOCustom {
    ServiceProviderCommentDto addServiceProviderComment(ServiceProviderCommentEntity serviceProviderComment);
    ServiceProviderCommentDto updateServiceProviderComment(ServiceProviderCommentEntity serviceProviderComment);
    ServiceProviderCommentDto findServiceProviderComment(int commentID);
    List<ServiceProviderCommentDto> findServiceProviderComments(int serviceProviderID);
    boolean deleteServiceProviderComment(int commentID);
}
