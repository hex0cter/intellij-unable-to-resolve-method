package com.example.kirk.report.events

import com.example.kirk.step.ExecutionType
import com.example.kirk.step.Step

/**
 * Triggered right before a step starts
 * It provides a timestamp and event type
 * the step instance
 * the step execution method
 * the arguments passed to the step
 */
class StepStarted extends StepEvent {
    Map args

    StepStarted(Step step, ExecutionType executionType, Map args) {
        super(step, executionType)
        this.args = args
    }
}
