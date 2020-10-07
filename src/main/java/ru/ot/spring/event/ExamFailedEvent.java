package ru.ot.spring.event;

import org.springframework.context.ApplicationEvent;

public class ExamFailedEvent extends ApplicationEvent {

    private final String payload;

    public String getPayload() {
        return payload;
    }

    public ExamFailedEvent(Object source) {
        super(source);
        payload = "ExamFailedEvent";
    }

}
