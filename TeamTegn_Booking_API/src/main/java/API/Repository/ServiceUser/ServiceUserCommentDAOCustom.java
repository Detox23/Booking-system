package API.Repository.ServiceUser;

import API.Database_Entities.ServiceUserCommentEntity;

public interface ServiceUserCommentDAOCustom  {
    Iterable<ServiceUserCommentEntity> listAll();
    Iterable<ServiceUserCommentEntity> findAllByServiceUserId(int serviceUserId);
    ServiceUserCommentEntity findOne(int id);
    ServiceUserCommentEntity addOnce(ServiceUserCommentEntity serviceProvider);
    ServiceUserCommentEntity update(ServiceUserCommentEntity serviceProviderComment);
    boolean deleteOne(int id, int commentID);
}
