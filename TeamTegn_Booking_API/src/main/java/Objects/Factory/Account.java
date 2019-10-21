package Objects.Factory;

import java.util.ArrayList;

public class Account implements Person {


    private String name;
    private int id;
    public Account(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void print() {
        System.out.printf("%s, %d", name, id);
    }
}
