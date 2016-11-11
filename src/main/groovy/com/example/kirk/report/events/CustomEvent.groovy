package com.example.kirk.report.events

/**
 * Allows framework users to implement their own event
 * for signaling listeners
 */
class CustomEvent extends KirkEvent {
    String name
    Object data

    /**
     * Create a new custom event.
     * @name can be used as a subtype (to distinguish among different custom events)
     * @data anything that the emitter wants to share
     */
    CustomEvent(String name, Object data) {
        Object
        this.name = name
        this.data = data
    }
}
