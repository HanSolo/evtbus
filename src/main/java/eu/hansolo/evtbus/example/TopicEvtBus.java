package eu.hansolo.evtbus.example;

import eu.hansolo.evtbus.EvtBus;
import eu.hansolo.evtbus.Subscriber;
import eu.hansolo.toolbox.evt.Evt;
import eu.hansolo.toolbox.evt.EvtObserver;
import eu.hansolo.toolbox.evt.EvtType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class TopicEvtBus implements EvtBus {
    private final Map<EvtType, List<Subscriber>> subscribers = new ConcurrentHashMap<>();


    @Override public <T extends Evt> void publish(final T evt) {
        final EvtType type = evt.getEvtType();
        subscribers.entrySet()
                   .stream()
                   .filter(entry -> entry.getKey().equals(TopicEvt.ANY))
                   .forEach(entry -> entry.getValue().forEach(observer -> observer.handle(evt)));
        if (subscribers.containsKey(type) && !type.equals(TopicEvt.ANY)) {
            subscribers.get(type).forEach(subscriber -> subscriber.handle(evt));
        }
    }

    @Override public void subscribe(final Subscriber subscriber) {
        final EvtType evtType = subscriber.getEvtType();
        if (!subscribers.containsKey(evtType)) { subscribers.put(evtType, new CopyOnWriteArrayList<>()); }
        if (!subscribers.get(evtType).contains(subscriber)) { subscribers.get(evtType).add(subscriber); }
    }

    @Override public void unsubscribe(final Subscriber subscriber) {
        final EvtType evtType = subscriber.getEvtType();
        if (subscribers.containsKey(evtType)) {
            if (subscribers.get(evtType).contains(subscriber)) {
                subscribers.get(evtType).remove(subscriber);
            }
        }
    }
}
