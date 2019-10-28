package Objects.Factory;

public class FactoryProvider {
    public static IAbstractFactory getFactory(String choice){
        if("Person".equalsIgnoreCase(choice)){
            return new PersonFactory();
        }
        else if("Assignment".equalsIgnoreCase(choice)){
            return new AssignmentFactory();
        }
        return null;
    }
}
