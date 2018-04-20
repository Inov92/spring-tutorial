package com.epam.spring.core.events;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    private Random rnd;
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date, DateFormat dateFormat) {
        rnd = new Random();
        id = rnd.nextInt();
        msg = "Message is not set for this event";
        this.date = date;
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Event[" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                ']';
    }
}
