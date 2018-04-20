package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger implements IEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName,int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }
    
    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize){
            writeElementsFromCache();
            cache.clear();
        }
    }

    private void writeElementsFromCache() {
        for (Event event: cache){
            super.logEvent(event);
        }
    }
    private void destroy(){
        if (!cache.isEmpty()){
            writeElementsFromCache();
        }
    }
}
