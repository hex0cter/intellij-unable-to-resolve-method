package com.example.kirk.core

/**
 * Common meta-data attributes for test entities (Step, Test, Suite, etc)
 * Useful for reporting, filtering, and documentation building
 */
interface Reportable {
    String getName()
    String getDescription()
    List<String> getTags()
}
