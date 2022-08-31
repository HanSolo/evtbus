package eu.hansolo.evtbus;

public class EvtBus implements EventBus {

    private Group eventHandlers = new Group();

    @Override public <T extends Evt> Subscriber addEventHandler(EvtType<T> eventType, EvtObserver<? super T> eventHandler) {
        eventHandlers.addEventHandler(eventType, eventHandler);
        return new Subscriber(this, eventType, (EventHandler<? super Evt>) eventHandler);
    }

    @Override public <T extends Evt> void removeEventHandler(EvtType<T> eventType, EvtObserver<? super T> eventHandler) {
        eventHandlers.removeEventHandler(eventType, eventHandler);
    }

    @Override public void fireEvent(Evt event) { eventHandlers.fireEvent(event); }
}
