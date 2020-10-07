package ru.ot.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ExamPassedListner implements ApplicationListener<ExamPassedEvent> {


    @Override
    public void onApplicationEvent(ExamPassedEvent examPassedEvent) {
        System.out.println(String.format("- %s", examPassedEvent.getPayload()));
    }

}
