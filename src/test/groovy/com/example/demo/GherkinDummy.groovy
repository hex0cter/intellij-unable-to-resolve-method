package com.example.demo

import com.klarna.kirk.UseSteps
import com.example.kirk.spock.KirkSpecification
import com.example.kirk.step.DummyClass


class DummyTest extends KirkSpecification implements UseSteps {

    def setup() {
        use(DummyClass)
    }

    def 'This test shows IntelliJ cannot resolve dummyMethod'() {
        given: 'Just a dummy method call'
            dummyMethod()
        when: 'nothing interests here'
            def i = 1

        then: 'nothing here either'
            assert i == 1;
    }
}
