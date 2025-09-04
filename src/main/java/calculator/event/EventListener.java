package calculator.event;

@FunctionalInterface
public interface EventListener {
    void onEvent(Event event);
}