package com.example.kirk.report

import com.example.kirk.report.events.CustomEvent
import com.example.kirk.report.events.StepEnded
import com.example.kirk.report.events.StepPost
import com.example.kirk.report.events.StepStarted

/**
 * Custom observers must implement this interface
 * (see Publisher)
 */
interface Observer {
    void onCustomEvent(CustomEvent event)
    void onStepStarted(StepStarted event)
    void onStepEnded(StepEnded event)
    void onStepPost(StepPost event)
}
