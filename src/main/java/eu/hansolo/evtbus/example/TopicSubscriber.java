package eu.hansolo.evtbus.example;

import eu.hansolo.evtbus.Subscriber;
import eu.hansolo.toolbox.evt.Evt;
import eu.hansolo.toolbox.evt.EvtType;


public class TopicSubscriber implements Subscriber {
    private final EvtType evtType;


    public TopicSubscriber(final EvtType evtType) {
        this.evtType = evtType;
    }


    @Override public EvtType<Evt> getEvtType() { return evtType; }

    @Override public void handle(final Evt evt) {
        Topic    topic    = (Topic) evt.getSource();
        TopicEvt topicEvt = (TopicEvt) evt;
        System.out.println(topic.getName() + " -> " + topicEvt.getMsg().getTxt());
    }
}
