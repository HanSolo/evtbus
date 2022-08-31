package eu.hansolo.evtbus;

public final class Subscriber {

    private EventBus                    eventBus;
    private EvtType<? extends Evt>  eventType;
    private EvtObserver<? super Evt> eventHandler;


    Subscriber(final EventBus EVENT_BUS, final EvtType<? extends Evt> TYPE, final EvtObserver<? super Evt> HANDLER) {
        eventBus     = EVENT_BUS;
        eventType    = TYPE;
        eventHandler = HANDLER;
    }

    public void unsubscribe() { eventBus.removeEventHandler(eventType, eventHandler); }
}
