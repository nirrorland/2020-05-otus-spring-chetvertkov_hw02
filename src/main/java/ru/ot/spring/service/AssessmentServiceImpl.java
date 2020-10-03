package ru.ot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ot.spring.config.YamlConf;
import ru.ot.spring.dao.QuestionDao;
import ru.ot.spring.domain.Question;

import java.io.IOException;
import java.util.List;

@Component
public class AssessmentServiceImpl implements AssessmentService {

    final private QuestionDao questionDao;
    final private ConsoleIOService consoleIOService;
    final private YamlConf conf;

    private List<Question> questions = null;

    @Autowired
    public AssessmentServiceImpl(QuestionDao questionDao, ConsoleIOService consoleIOService, YamlConf conf) {
        this.questionDao = questionDao;
        this.consoleIOService = consoleIOService;
        this.conf = conf;
    }

    @Override
    public void startAssessment() {

        try {
            questions = questionDao.getQuestionList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((questions != null) && (questions.size() > 0)) {
            consoleIOService.bundleOut("loadfile.success");
            consoleIOService.bundleOut("input.firstname");
            String firstName = readAnswer();
            consoleIOService.bundleOut("input.lastname");
            String lastName = readAnswer();
            consoleIOService.bundleOut("hello", new String[] {firstName, lastName});

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
            } else {
                consoleIOService.bundleOut("result.bad");
            }

        } else {
            consoleIOService.bundleOut("loadfile.error");
        }
    }

    private String readAnswer() {
        return consoleIOService.read();
    }
}
