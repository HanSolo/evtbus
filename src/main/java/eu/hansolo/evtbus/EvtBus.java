package eu.hansolo.evtbus;

import eu.hansolo.toolbox.evt.Evt;


public interface EvtBus {

    <T extends Evt> void publish(T evt);

    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);
}
