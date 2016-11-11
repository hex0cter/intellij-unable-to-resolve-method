package com.example.kirk.report

import com.example.kirk.report.events.Event
import com.example.kirk.report.events.StepStarted
import com.example.kirk.report.events.CustomEvent
import com.example.kirk.report.events.EventType
import com.example.kirk.report.events.StepEnded
import com.example.kirk.report.events.StepPost

/**
 * The publisher has two main responsibilities
 *  * Registering entities interested in events
 *    occurring in Kirk
 *  * Notifying those entities when stuff happens in
 *    Kirk
 *
 *  This allows a clean implementation of "plugins"
 *  without the need of coupling them with the test project
 *  or Kirk.
 *
 *  Usage of this could be (but not limited to):
 *    * Reporting results in different formats: json, xml, etc
 *    * Reporting results to Splunk or ElasticSearch
 *    * Reporting results via email
 *    * Create a ticket in a bug-tracker when something fails
 *    * Create test debugging tools
 *    * Modify step arguments and outcomes on the fly (e.g. for fuzzing)
 *    * Execute tests distributed as part of a perfomance test
 *      and collect metric on time taken by each single step in a test
 *    * Launch a rocket and put a monkey into space if all the tests pass
 */
class Publisher {
    private final Map<EventType, List<Observer>> observers
    Publisher() {
        observers = [:]
        EventType.values().each { eventType -> observers[eventType] = [] }
    }

    /**
     * Subscribes an observer to a particular event
     * @param observer: The instance to be notified when the event occurs
     * @param eventType: The kind of event the observer is interested in
     */
    def subscribe(Observer observer, EventType eventType) {
        observers[eventType].add(observer)
    }

    /**
     * Subscribes an observer to all the type of events
     * @param observer: The instance to be notified when an event occurs
     */
    def subscribeToAll(Observer observer) {
        EventType.values().each { eventType ->
            subscribe(observer, eventType)
        }
    }

    /**
     * Triggers a custom event, and notifies subscribers
     * @param eventName: The custom event type.
     * @param data: Any information the emitter wants to share (defaults to null)
     */
    void customEvent(String eventName, Object data = null) {
        notifySubscribers(new CustomEvent(eventName, data))
    }

    /**
     * Triggers an event and notifies the observers of that even
     * @param event The event object containing all the relevant information
     */
    void notifySubscribers(Event event) {
        switch (event.type) {
            case EventType.STEP_STARTED:
                subscribersOfEventType(event.type) { Observer observer -> observer.onStepStarted((StepStarted) event) }
                break
            case EventType.STEP_ENDED:
                subscribersOfEventType(event.type) { Observer observer -> observer.onStepEnded((StepEnded) event) }
                break
            case EventType.STEP_POST:
                subscribersOfEventType(event.type) { Observer observer -> observer.onStepPost((StepPost) event) }
                break
            case EventType.CUSTOM:
                subscribersOfEventType(event.type) { Observer observer -> observer.onCustomEvent((CustomEvent) event) }
        }
    }

    private subscribersOfEventType(EventType eventType, Closure closure) {
        observers[eventType].each { Observer observer ->
            closure(observer)
        }
    }
}
