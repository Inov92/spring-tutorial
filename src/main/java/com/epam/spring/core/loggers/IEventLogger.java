package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

public interface IEventLogger {
    void logEvent(Event event);
}
