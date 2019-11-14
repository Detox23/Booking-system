package API.Services.ServiceProviderService;
import API.Exceptions.UpdatePatchException;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdate;
import Shared.ToReturn.ServiceProviderDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IServiceProviderService {
    List<ServiceProviderDto> list();
    ServiceProviderDto findServiceProvider(int id);
    ServiceProviderDto addServiceProvider(ServiceProviderForCreationDto serviceProvider);
    ServiceProviderDto updateServiceProvider(ServiceProviderForUpdate serviceProvider);
    boolean deleteServiceProvider(int id);
}
