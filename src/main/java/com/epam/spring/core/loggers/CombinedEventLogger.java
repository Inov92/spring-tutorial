package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

import java.util.Collection;

public class CombinedEventLogger implements IEventLogger {
    private Collection<IEventLogger> loggers;

    public CombinedEventLogger(Collection<IEventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for(IEventLogger logger: loggers){
            logger.logEvent(event);
        }
    }
}
