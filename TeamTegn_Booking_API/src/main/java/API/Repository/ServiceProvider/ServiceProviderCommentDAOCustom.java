package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderCommentEntity;
import Shared.ToReturn.ServiceProviderCommentDto;

import java.beans.IntrospectionException;
import java.util.List;

public interface ServiceProviderCommentDAOCustom {
    ServiceProviderCommentDto addServiceProviderComment(ServiceProviderCommentEntity serviceProviderComment);
    ServiceProviderCommentDto updateServiceProviderComment(ServiceProviderCommentEntity serviceProviderComment);
    ServiceProviderCommentDto findServiceProviderComment(int id, int commentID);
    List<ServiceProviderCommentDto> findServiceProviderComments(int serviceProviderID);
    boolean deleteServiceProviderComment(int id, int commentID);
}
