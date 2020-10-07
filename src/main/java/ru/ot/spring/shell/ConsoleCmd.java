package ru.ot.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.ot.spring.service.AssessmentService;

@ShellComponent
public class ConsoleCmd {

    final private AssessmentService assessmentService;

    @Autowired
    public ConsoleCmd(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @ShellMethod(value = "Login", key = {"l", "login"})
    public void inputUsername() {
        assessmentService.login();
    }

    @ShellMethod(value = "Start test", key = {"st", "start-test"})
    @ShellMethodAvailability(value = "isReadyForTest")
    public void startTest() {
        assessmentService.startAssessmentFromShell();
    }

    private Availability isReadyForTest() {
        return assessmentService.isUserLoggedIn() == false? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
