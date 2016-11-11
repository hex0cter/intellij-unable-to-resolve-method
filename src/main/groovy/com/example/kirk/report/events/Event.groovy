package com.example.kirk.report.events

/**
 * Every kind of event must implement this interface
 * An event will at least have a timestamp and a type
 */
interface Event {
    long getTimestamp()
    EventType getType()
}
