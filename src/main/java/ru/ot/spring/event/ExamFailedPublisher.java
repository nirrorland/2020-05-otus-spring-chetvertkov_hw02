package ru.ot.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ExamFailedPublisher implements EventPublisher {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public ExamFailedPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish() {
        publisher.publishEvent(new ExamFailedEvent(this));
    }
}
