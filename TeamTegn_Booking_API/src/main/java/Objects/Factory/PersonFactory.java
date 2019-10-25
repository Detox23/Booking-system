package Objects.Factory;

import Shared.AccountForCreationDto;

public class PersonFactory {
    public Person getPerson(String kindOfPerson, String name, int id){
        if(kindOfPerson == null){
            return null;
        }

        if(kindOfPerson.equalsIgnoreCase("Account")){
            return new AccountForCreationDto(name, id);
        }

        if(kindOfPerson.equalsIgnoreCase("ServiceProvider")){
            return new ServiceProvider(name, id);
        }

        if(kindOfPerson.equalsIgnoreCase("ServiceUser")){
            return new ServiceUser(name, id);
        }
        return null;
    }
}
