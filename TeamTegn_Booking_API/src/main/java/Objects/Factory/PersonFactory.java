package Objects.Factory;

import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceUserForCreationDto;

public class PersonFactory implements IAbstractFactory<IPerson> {

    @Override
    public IPerson create(String type) {
        if(type.equalsIgnoreCase("Account")){
            return new AccountForCreationDto();
        }

        if(type.equalsIgnoreCase("ServiceProvider")){
            return new ServiceProviderForCreationDto();
        }

        if(type.equalsIgnoreCase("ServiceUser")){
            return new ServiceUserForCreationDto();
        }
        return null;
    }
}
