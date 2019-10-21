package Objects.Factory;

import java.util.ArrayList;

public class Account implements Person {

    private ArrayList attributes;
    public Account(ArrayList listOfAttributes) {
        attributes = listOfAttributes;
    }

    @Override
    public void print() {
        System.out.printf("%s, %s", attributes.get(0), attributes.get(1));
    }
}
