package API.Repository.SystemUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.CityPostcodesEntity;
import API.Database_Entities.SystemUserEntity;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import Shared.ForCreation.SystemUserForCreationDto;
import Shared.ForCreation.SystemUserForUpdateDto;
import Shared.ToReturn.SystemUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SystemUserDAOImpl implements SystemUserDAOCustom {

    private SystemUserDAO systemUserDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    private CityPostcodesDAO cityPostcodesDAO;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
        this.systemUserDAO = systemUserDAO;
    }



    @Override
    public SystemUserDto addSystemUser(SystemUserEntity systemUser) {
        if(systemUser.getCity() == null && systemUser.getPostcode() != null){
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findByPostcodeIs(systemUser.getPostcode());
            if(found.isPresent()){
                systemUser.setCity(found.get().getCity());
            }
        }
        if(systemUser.getPostcode() == null && systemUser.getCity() != null){
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findByCityIs(systemUser.getCity());
            if(found.isPresent()){
                systemUser.setPostcode(found.get().getPostcode());
            }
        }
        return null;
    }

    @Override
    public SystemUserDto updateSystemUser(SystemUserEntity systemUser) {
        return null;
    }

    @Override
    public boolean deleteSystemUser(int id) {
        return false;
    }

    @Override
    public List<SystemUserDto> listSystemUsers() {
        return null;
    }

    @Override
    public SystemUserDto findSystemUser(int id) {
        return null;
    }


}
