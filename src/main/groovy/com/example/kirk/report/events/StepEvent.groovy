package com.example.kirk.report.events

import com.example.kirk.step.ExecutionType
import com.example.kirk.step.Step

/**
 * Base implementation of  any Step related event
 * It adds the step instance to the event
 */
class StepEvent extends KirkEvent {
    Step step
    ExecutionType executionType

    /**
     * This class should not be instantiated directly. But
     * since its interface is fully implemented, we rather
     * make it's constructor protected than using the
     * abstract classifier.
     */
    protected StepEvent(Step step, ExecutionType executionType) {
        Object
        this.step = step
        this.executionType = executionType
    }
}
