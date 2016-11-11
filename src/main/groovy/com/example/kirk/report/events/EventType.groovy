package com.example.kirk.report.events

/**
 * Available Kirk type of events that may occur
 */
enum EventType {
    STEP_STARTED(StepStarted),
    STEP_ENDED(StepEnded),
    STEP_POST(StepPost),
    CUSTOM(CustomEvent)

    Class<? extends Event> eventClass

    EventType(Class<? extends Event> eventClass) {
        this.eventClass = eventClass
    }

    static EventType typeForClass(Class<? extends Event> eventClass) {
        values().find { EventType type -> type.eventClass == eventClass }
    }
}
