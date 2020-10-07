package ru.ot.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExamFailedListner {

    @EventListener
    public void onApplicationEvent(ExamFailedEvent examFailedEvent) {
        System.out.println(String.format("- %s", examFailedEvent.getPayload()));
    }

}
