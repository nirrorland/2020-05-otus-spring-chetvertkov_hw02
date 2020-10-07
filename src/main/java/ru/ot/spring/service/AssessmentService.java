package ru.ot.spring.service;

public interface AssessmentService {
    void startAssessmentWithoutShell();
    void startAssessmentFromShell();
    void login();
    boolean isUserLoggedIn();
}
