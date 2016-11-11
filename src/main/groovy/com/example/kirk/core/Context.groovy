package com.example.kirk.core

import com.example.kirk.report.Publisher

/**
 * A Context represents WHERE an execution entity is being run
 * An execution entity might be a Step, a Test, a Suite or whatever
 * abstraction makes sense in a test framework
 *
 * A context is meant to provide:
 *  * traceability among execution entities. E.g.:
 *      Suite -> Text X -> Step Y
 *
 *  * execution status management for whole entity chain. E.g.:
 *      If a step fails, then the test also fails, an also the suite
 *
 *  * a context can also be used to provide access to environmental or
 *    global settings, without having to depend on singletons or static code
 *    (helping support for parallel test execution) and avoiding the need of
 *    passing config objects back an forth. This should not be overused though
 *    as it may break encapsulation.
 *
 *  * finally a context, should help implementing plugins (e.g. for flow control,
 *    reporting, etc)
 */
interface Context {
    /**
     * The super context in which this context running
     * @return A Context object or null for the root context (e.g. Suite)
     */
    Context getContext()

    /**
     * Get access to this context's publisher
     * @return the publisher instance for this context
     */
    Publisher getPublisher()

    /**
     * Get access to this context's configuration
     * @return a ConfigObject instance for this context
     */
    ConfigObject getConfig()
}
