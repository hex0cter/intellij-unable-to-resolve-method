package com.example.kirk.core

import com.example.kirk.step.Step

/**
 * A context that supports instantiation of Steps passing itself as a Context.
 * For instance a Suite can be a Context but not a StepRunningContext since it
 * shouldn't execute steps directly.
 * However, a Test (or Spec) can be a StepRunningContext. Also a Step is
 * a StepRunningContext since sub-steps can be invoked to build macros
 */
interface StepRunningContext extends Context {
    /**
     * A context that supports instantiation of Steps passing
     * itself as a Context
     * @param stepClass
     * @return an instance of stepClass with this as Context
     */
    def <T extends Step> Step step(Class<T> stepClass)
}
