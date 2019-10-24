package Objects.Factory;

import java.util.ArrayList;

public class ServiceUser implements Person  {
    private String name;
    private int id;

    public ServiceUser(String name, int id) {
        this.name = name;
        this.id = id;
    }



    @Override
    public void print() {

    }
}
