package com.example.kirk.spock

import com.example.kirk.report.Observer
import org.spockframework.runtime.IRunListener
import org.spockframework.runtime.extension.IGlobalExtension

/**
 * Single interface combining events from Spock and Kirk
 *
 * TODO: in Spock there's currently no callbacks
 * support for block events (given, when, then).
 *
 * A PR has been created for that but it is not merged yet:
 * https://github.com/spockframework/spock/pull/111
 */
interface KirkSpockListener extends IGlobalExtension, IRunListener, Observer {

    /**
     * To be called when configuration is being loaded, so listeners can include their own
     * settings to the main context's config object.
     * @param configObject, the configObject to add settings too
     */
    void onConfigLoading(ConfigObject configObject)
}
