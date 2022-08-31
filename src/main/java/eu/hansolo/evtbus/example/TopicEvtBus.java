package eu.hansolo.evtbus.example;

import eu.hansolo.evtbus.EvtBus;
import eu.hansolo.evtbus.Subscriber;
import eu.hansolo.evtbus.Topic;
import eu.hansolo.toolbox.evt.Evt;
import eu.hansolo.toolbox.evt.EvtObserver;
import eu.hansolo.toolbox.evt.EvtType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class TopicEvtBus implements EvtBus {
    private final Map<Topic, Map<EvtType, List<Subscriber>>> topicSubscribers = new ConcurrentHashMap<>();


    // ******************** Methods *******************************************
    @Override public <T extends Evt> void publish(final Topic topic, final T evt) {
        final EvtType type = evt.getEvtType();
        if (topicSubscribers.containsKey(topic)) {
            Map<EvtType, List<Subscriber>> subscribers = topicSubscribers.get(topic);
            subscribers.entrySet()
                       .stream()
                       .filter(entry -> entry.getKey().equals(TopicEvt.ANY))
                       .forEach(entry -> entry.getValue().forEach(observer -> observer.handle(evt)));
            if (subscribers.containsKey(type) && !type.equals(TopicEvt.ANY)) {
                subscribers.get(type).forEach(subscriber -> subscriber.handle(evt));
            }
        }
    }

    @Override public void subscribe(final Topic topic, final Subscriber subscriber) {
        final EvtType evtType = subscriber.getEvtType();
        if (!topicSubscribers.containsKey(topic)) {
            topicSubscribers.put(topic, new ConcurrentHashMap<>());
        }
        if (!topicSubscribers.get(topic).containsKey(evtType)) {
            topicSubscribers.get(topic).put(evtType, new CopyOnWriteArrayList<>());
        }
        if (!topicSubscribers.get(topic).get(evtType).contains(subscriber)) {
            topicSubscribers.get(topic).get(evtType).add(subscriber);
        }
    }

    @Override public void unsubscribe(final Topic topic, final Subscriber subscriber) {
        final EvtType evtType = subscriber.getEvtType();
        if (topicSubscribers.containsKey(topic)) {
            if (topicSubscribers.get(topic).containsKey(evtType)) {
                if (topicSubscribers.get(topic).get(evtType).contains(subscriber)) {
                    topicSubscribers.get(topic).get(evtType).remove(subscriber);
                }
            }
        }
    }
}