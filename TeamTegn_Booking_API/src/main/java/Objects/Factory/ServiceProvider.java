package Objects.Factory;

import java.util.ArrayList;

public class ServiceProvider implements Person {

    private String name;
    private int id;

    public ServiceProvider(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void print() {

    }
}
