package com.example.kirk.report.events

/**
 * Base implementation of Event, getting the
 * current time automatically and deducing the
 * event type from the subclass type
 */
class KirkEvent implements Event {
    long timestamp
    EventType type

    /**
     * This class should not be instantiated directly. But
     * since its interface is fully implemented, we rather
     * make it's constructor protected than using the
     * abstract classifier.
     */
    protected KirkEvent() {
        timestamp = System.currentTimeMillis()
        type = EventType.typeForClass(getClass())
    }
}
