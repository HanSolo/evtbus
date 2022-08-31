package eu.hansolo.evtbus.example;

import eu.hansolo.evtbus.Subscriber;
import eu.hansolo.evtbus.Topic;
import eu.hansolo.toolbox.evt.Evt;
import eu.hansolo.toolbox.evt.EvtType;


public class TopicSubscriber implements Subscriber {
    private final String  name;
    private final EvtType evtType;


    // ******************** Constructors **************************************
    public TopicSubscriber(final String name, final EvtType evtType) {
        this.name    = name;
        this.evtType = evtType;
    }


    // ******************** Methods *******************************************
    public String getName() { return name; }

    @Override public EvtType<Evt> getEvtType() { return evtType; }

    @Override public void handle(final Evt evt) {
        TopicEvt topicEvt = (TopicEvt) evt;
        System.out.println(name + ": " + " -> " + topicEvt.getMsg().getTxt());
    }
}
