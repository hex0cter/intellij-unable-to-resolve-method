package com.example.kirk.spock

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Annotation of Listeners (Subscribers) to be loaded when a Spock execution starts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface LoadListener {
    /**
     * Annotated classes with higher prio will be loaded first
     *
     */
    int prio() default 0
}
