package com.example.kirk.step

import com.example.kirk.core.Reportable
import com.example.kirk.core.StepRunningContext

/**
 * Any class representing a runnable step
 * must implement this interface.
 * Also a step is itself a Context, since other
 * steps can be invoked from it (Macros)
 */
interface Step extends Reportable, StepRunningContext {
    /**
     * Perform the actual implementation of the step
     * @param args: Arguments needed by the step
     * @return [Object] whatever the step wants to return
     */
    def run(Map args)

    /**
     * Default step executor, the step passes if no exceptions are thrown
     * otherwise the step fails
     *
     * Evaluates the given closure right after the step finishes.
     * The closure will be invoked with:
     *      * The step instance
     *      * The step's outcome
     *
     * Exceptions raised within the closure will make the step fail
     *
     * @param args: a Map with the arguments needed by the step
     * @param runAfter: the closure to be invoked with step instance and outcome
     * @return
     */
    def execute(Map args, Closure runAfter)

    /**
     * Same as "execute" without a closure
     */
    def execute(Map args)

    /**
     * Same as "execute" without arguments
     */
    def execute(Closure runAfter)

    /**
     * Same as "execute" without a closure nor arguments
     */
    def execute()

    /**
     * Creates a post event and notifies observers
     */
    def post(String key, Object value)

    /**
     * Adds expected exceptions to step
     */
    Step expectException(Class<? extends Throwable>... t)

    /**
     * Returns a list of the expected exception classes for this Step
     */
    List<Class<? extends Throwable>> getExpectedExceptions()
}
