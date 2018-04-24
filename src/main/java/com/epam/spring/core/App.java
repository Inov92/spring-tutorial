package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.enums.EventType;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import com.epam.spring.core.loggers.IEventLogger;
import com.epam.spring.core.loggers.StatisticsAspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class App {
    @Autowired
    private Client client;
    private IEventLogger defaultLogger;
    private Event event;
    private App app;
    private Map<EventType,IEventLogger> loggers;
    private StatisticsAspect statisticsAspect;

    public App(Client client, IEventLogger defaultLogger, Map<EventType,IEventLogger> loggers){
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void setStatisticsAspect(StatisticsAspect statisticsAspect) {
        this.statisticsAspect = statisticsAspect;
    }

    public void logEvent(EventType eventType, Event event){
        IEventLogger logger = loggers.get(eventType);
        if (logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public void showStatistics(){
        Map<Class<?>, Integer> counter = statisticsAspect.getCounter();
        for(Map.Entry<Class<?>, Integer> entry:counter.entrySet()){
            String className = entry.getKey().getSimpleName();
            Integer count = entry.getValue();
            System.out.println(className + " - " + count);
        }

    }

}
