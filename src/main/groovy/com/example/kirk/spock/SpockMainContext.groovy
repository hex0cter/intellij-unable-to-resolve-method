package com.example.kirk.spock

import com.example.kirk.core.Context
import com.example.kirk.report.Publisher
import org.reflections.Reflections
import org.spockframework.runtime.extension.IGlobalExtension
import org.spockframework.runtime.model.SpecInfo

import java.lang.annotation.Annotation

/**
 * Root Context for a Spock execution. This can get loaded automatically
 * by adding 'SpockMainContext'
 * to 'resource/META-INF.services/org.spockframework.runtime.extension.IGlobalExtension'
 */
class SpockMainContext implements Context, IGlobalExtension {
    Publisher publisher = new Publisher()
    ConfigObject config = new ConfigObject()

    final Context context = null // This is the root context, so there's no parent
    private final List<KirkSpockListener> listeners = []

    static SpockMainContext instance

    @Override
    void start() {
        instance = this
        loadSubscribers()
        loadConfigurations()
        listeners.each { listener ->
            listener.start()
        }
    }

    @Override
    void visitSpec(SpecInfo specInfo) {
        listeners.each { listener ->
            specInfo.addListener(listener)
            listener.visitSpec(specInfo)
        }
    }

    @Override
    void stop() {
        listeners.each { listener ->
            listener.stop()
        }
    }

    /**
     * Finds all the subclasses of KirkSpockListener annotated with @LoadListener
     * Sorts them by prio, instanced them and subcribes them to Spock and Kirk events
     */
    private void loadSubscribers() {
        Reflections reflections = new Reflections('')

        Set<Class<? extends KirkSpockListener>> candidates = reflections.getSubTypesOf(KirkSpockListener)
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(LoadListener)

        Map<Integer, List<Class<? extends KirkSpockListener>>> toBeLoaded = [:].withDefault { [] }

        candidates.each { Class<? extends  KirkSpockListener> candidate ->
            if (annotated.contains(candidate)) {
                toBeLoaded[getListenerPrio(candidate)].add(candidate)
            }
        }

        (toBeLoaded.sort { a, b -> b.key <=> a.key } )*.value
                .each { List<Class<? extends  KirkSpockListener>> classes ->
            classes.each { Class<? extends  KirkSpockListener> listenerClass ->
                KirkSpockListener listener = instanceListener(this, listenerClass)
                if (listener != null) {
                    listeners.add(listener)
                    publisher.subscribeToAll(listener)
                }
            }
        }
    }

    private static int getListenerPrio(Class<? extends  KirkSpockListener> listener) {
        Annotation annotation = listener.annotations.find { Annotation a ->
            a instanceof LoadListener
        }
        ((LoadListener) annotation).prio()
    }

    @SuppressWarnings('UnnecessaryGetter')
    private static <T extends KirkSpockListener> KirkSpockListener instanceListener(Context context,
                                                                                    Class<T> listenerClass) {
        try {
            return listenerClass.getConstructor(Context).newInstance(context)
        }
        catch (NoSuchMethodException e) {
            return listenerClass.getConstructor().newInstance()
        }
    }

    private void loadConfigurations() {
        listeners.each { listener ->
            listener.onConfigLoading(config)
        }
    }
}
