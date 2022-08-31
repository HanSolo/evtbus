package eu.hansolo.evtbus;

import eu.hansolo.toolbox.evt.Evt;
import eu.hansolo.toolbox.evt.EvtType;


public interface Subscriber {

    EvtType<Evt> getEvtType();

    void handle(Evt evt);
}
