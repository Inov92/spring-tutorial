package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticsAspect {

    private Map<Class<?>, Integer> counter = new HashMap<Class<?>, Integer>();
    private static final int MAX_ALLOWED = 3;
    private FileEventLogger fileEventLogger;

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods(){}

    @Pointcut("allLogEventMethods() && within(*.*Console*Logger)")
    private void logEventInsideConsoleLoggers() {}

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp){
        Class<?> clazz = jp.getTarget().getClass();
        if(!counter.containsKey(clazz)){
            counter.put(clazz,0);
        }
        counter.put(clazz, counter.get(clazz)+1);
    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE : " +
                joinPoint.getTarget()
                        .getClass().getSimpleName()
                + " " +
                joinPoint.getSignature()
                        .getName());
    }

    @Around("logEventInsideConsoleLoggers() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt){
        if (counter.get(ConsoleEventLogger.class) < MAX_ALLOWED) {
            try {
                jp.proceed(new Object[] {evt});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            fileEventLogger.logEvent((Event)evt);
        }
    }
}
