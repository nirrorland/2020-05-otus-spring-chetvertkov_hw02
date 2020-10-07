package ru.ot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ot.spring.config.YamlConf;
import ru.ot.spring.dao.QuestionDao;
import ru.ot.spring.domain.Question;
import ru.ot.spring.event.ExamFailedListner;
import ru.ot.spring.event.ExamFailedPublisher;
import ru.ot.spring.event.ExamPassedPublisher;

import java.io.IOException;
import java.util.List;

@Component
public class AssessmentServiceImpl implements AssessmentService {

    final private QuestionDao questionDao;
    final private ConsoleIOService consoleIOService;
    final private YamlConf conf;
    final private ExamPassedPublisher examPassedPublisher;
    final private ExamFailedPublisher examFailedPublisher;

    private String firstName;
    private String lastName;

    private List<Question> questions = null;

    @Autowired
    public AssessmentServiceImpl(QuestionDao questionDao, ConsoleIOService consoleIOService, YamlConf conf, ExamPassedPublisher examPassedPublisher, ExamFailedPublisher examFailedPublisher) {
        this.questionDao = questionDao;
        this.consoleIOService = consoleIOService;
        this.conf = conf;
        this.examPassedPublisher = examPassedPublisher;
        this.examFailedPublisher = examFailedPublisher;
    }

    @Override
    public void startAssessmentWithoutShell() {
        login();
        startAssessmentFromShell();
    }

    @Override
    public void startAssessmentFromShell() {
        loadQuestions();
        doAssessment();
    }

    public void doAssessment() {
        if (isQuestionsLoadedSuccessfully()) {

            Integer correctAnswersCounter = 0;

            for (Question question : questions) {
                consoleIOService.out(question.getText());
                consoleIOService.bundleOut("answer.number");
                String answer = readAnswer();
                if (question.getCorrectAnswer().equals(answer)) {
                    correctAnswersCounter++;
                }
            }

            consoleIOService.bundleOut("total.score", new String[] {correctAnswersCounter.toString()});

            if (correctAnswersCounter >= conf.getMinscore()) {
                consoleIOService.bundleOut("result.good");
                examPassedPublisher.publish();
            } else {
                consoleIOService.bundleOut("result.bad");
                examFailedPublisher.publish();
            }

        } else {
            consoleIOService.bundleOut("loadfile.error");
        }
    }

    private void loadQuestions() {
        try {
            questions = questionDao.getQuestionList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login() {
        consoleIOService.bundleOut("loadfile.success");
        consoleIOService.bundleOut("input.firstname");
        this.firstName = readAnswer();
        consoleIOService.bundleOut("input.lastname");
        this.lastName = readAnswer();
        consoleIOService.bundleOut("hello", new String[] {this.firstName, this.lastName});
    }

    private boolean isQuestionsLoadedSuccessfully() {
        if ((questions != null) && (questions.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        if ((this.firstName != null) && (this.lastName != null) && (this.firstName.length() > 0) && (this.lastName.length() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    private String readAnswer() {
        return consoleIOService.read();
    }
}
