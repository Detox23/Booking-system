package Objects.Factory;
import java.util.ArrayList;
public class PersonFactory {
    public Person getPerson(String kindOfPerson, ArrayList listOfAttributes){
        if(kindOfPerson == null){
            return null;
        }

        if(kindOfPerson.equalsIgnoreCase("Account")){
            return new Account(listOfAttributes);
        }

        if(kindOfPerson.equalsIgnoreCase("ServiceProvider")){
            return new ServiceProvider(listOfAttributes);
        }

        if(kindOfPerson.equalsIgnoreCase("ServiceUser")){
            return new ServiceUser(listOfAttributes);
        }
        return null;
    }
}
