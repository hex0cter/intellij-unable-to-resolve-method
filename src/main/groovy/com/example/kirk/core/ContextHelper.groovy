package com.example.kirk.core

import com.example.kirk.step.Step

import java.lang.reflect.Constructor

/**
 * ContextHelper
 */
class ContextHelper {
    static void loadConfig(ConfigObject config, URL resource, String env = null) {
        ConfigSlurper slurper = env ? new ConfigSlurper(env) : new ConfigSlurper()
        config.merge(slurper.parse(resource))
    }

    static <T extends Step> Step stepInstance(Context context, Class<T> stepClass) {
        Constructor<T> constructor = stepClass.getConstructor(Context)
        constructor.newInstance(context)
    }
}
