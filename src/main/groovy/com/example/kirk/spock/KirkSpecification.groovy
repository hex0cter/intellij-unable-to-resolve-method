package com.example.kirk.spock

import com.example.kirk.core.ContextHelper
import com.example.kirk.core.StepRunningContext
import com.example.kirk.core.Context
import com.example.kirk.report.Publisher
import com.example.kirk.step.Step
import spock.lang.Specification

/**
 * Extends Spock's Specification to turn it into a Kirk StepRunningContext
 */
class KirkSpecification extends Specification implements StepRunningContext {
    @Override
    def <T extends Step> Step step(Class<T> stepClass) {
        ContextHelper.stepInstance(this, stepClass)
    }

    @Override
    Context getContext() {
        SpockMainContext.instance
    }

    @Override
    Publisher getPublisher() {
        context.publisher
    }

    @Override
    ConfigObject getConfig() {
        context.config
    }
}
