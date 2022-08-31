package eu.hansolo.evtbus;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;


public interface EventBus {

    <T extends Event> Subscriber addEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler);

    <T extends Event> void removeEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler);

    void fireEvent(Event event);
}
