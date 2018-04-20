package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

public class ConsoleEventLogger implements IEventLogger {
    public void logEvent(Event event){
        System.out.println(event.toString());
    }

}
