package ru.ot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ot.spring.dao.QuestionDao;
import ru.ot.spring.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class AssessmentServiceImpl implements AssessmentService {

    private QuestionDao questionDao;

    private List<Question> questions = null;

    @Value("${filename}")
    private String fileName;

    @Value("${minscore}")
    private Integer minScoreCount;

    @Autowired
    public AssessmentServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void startAssessment() {

        try {
            questions = questionDao.getQuestionList(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((questions != null) && (questions.size() > 0)) {
            System.out.println("Susseccfully read question file");
            System.out.println("Enter your firstName:");
            String firstName = readAnswer();
            System.out.println("Enter your lastName:");
            String lastName = readAnswer();
            int correctAnswersCounter = 0;

            System.out.println("Hello: " + firstName + " " + lastName);

            for (Question question : questions) {
                System.out.println(question.getText());
                System.out.println("Enter number of your answer:");
                String answer = readAnswer();
                if (question.getCorrectAnswer().equals(answer)) {
                    correctAnswersCounter++;
                }
            }

            System.out.println("Your total score: " + correctAnswersCounter);

            if (correctAnswersCounter >= minScoreCount) {
                System.out.println("You pass exam");
            } else {
                System.out.println("You failed exam");
            }

        } else {
            System.out.println("Error reading questions from file or it's empty");
        }

    }

    String readAnswer() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
