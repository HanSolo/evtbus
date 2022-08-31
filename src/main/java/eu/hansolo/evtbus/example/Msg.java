package eu.hansolo.evtbus.example;

public class Msg {
    private final String txt;


    public Msg(final String txt) {
        this.txt = txt;
    }


    public String getTxt() { return txt; }


    @Override public String toString() { return txt; }
}
