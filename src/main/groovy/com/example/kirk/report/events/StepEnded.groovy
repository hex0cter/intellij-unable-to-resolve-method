package com.example.kirk.report.events

import com.example.kirk.step.Status
import com.example.kirk.step.ExecutionType
import com.example.kirk.step.Step

/**
 * Triggered right after a step ends
 * It provides a timestamp and event type
 * the step instance
 * the step execution method
 * the step status
 * the step result (outcome)
 * any unhandled exception that might have been thrown by the step
 */
class StepEnded extends StepEvent {
    Status status
    Object result
    Throwable exception

    StepEnded(Step step, ExecutionType executionType, Status status, Object result, Throwable exception) {
        super(step, executionType)
        this.status = status
        this.result = result
        this.exception = exception
    }
}
