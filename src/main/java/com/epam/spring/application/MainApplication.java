package com.epam.spring.application;

import com.epam.spring.core.App;
import com.epam.spring.core.enums.EventType;
import com.epam.spring.core.events.Event;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        for (int i = 0; i < 5; i++) {
            Event event = (Event) ctx.getBean("event");
            app.logEvent(EventType.ERROR, event);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ctx.close();
    }
}
