package ru.ot.spring.event;

import org.springframework.context.ApplicationEvent;

public class ExamPassedEvent extends ApplicationEvent {

    private final String payload;

    public String getPayload() {
        return payload;
    }

    public ExamPassedEvent(Object source) {
        super(source);
        payload = "ExamPassedEvent";
    }

}
