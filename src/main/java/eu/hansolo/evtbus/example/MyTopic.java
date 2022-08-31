package eu.hansolo.evtbus.example;

public class MyTopic implements Topic {
    private String name;

    public MyTopic(final String name) {
        this.name = name;
    }


    @Override public String getName() { return name; }
}
