package calculator.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<Class<? extends Event>, List<EventListener>> listeners = new HashMap<>();

    public void subscribe(Class<? extends Event> eventType, EventListener listener) {
        // 해당 이벤트 타입에 대한 리스너 리스트가 없으면 새로 생성
        List<EventListener> eventListeners = listeners.computeIfAbsent(eventType, k -> new ArrayList<>());
        eventListeners.add(listener);
    }

    public void publish(Event event) {
        Class<?> eventType = event.getClass();

        List<EventListener> eventListeners = listeners.get(eventType);

        if (eventListeners == null || eventListeners.isEmpty()) {
            return;
        }

        for (EventListener listener : eventListeners) {
            listener.onEvent(event);
        }
    }
}