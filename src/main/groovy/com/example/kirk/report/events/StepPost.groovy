package com.example.kirk.report.events

import com.example.kirk.step.ExecutionType
import com.example.kirk.step.Step

/**
 * Event that helps with posting values
 */
class StepPost extends StepEvent {

    private final String key
    private final Object value

    StepPost(Step step, ExecutionType executionType, String key, Object value ) {
        super(step, executionType)
        this.value = value
        this.key = key
    }
}
