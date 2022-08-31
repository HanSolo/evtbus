package eu.hansolo.evtbus;

import eu.hansolo.toolbox.evt.Evt;


public interface EvtBus {

    <T extends Evt> void publish(Topic topic, T evt);

    void subscribe(Topic topic, Subscriber subscriber);

    void unsubscribe(Topic topic, Subscriber subscriber);
}
