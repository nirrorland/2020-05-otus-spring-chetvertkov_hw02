package ru.ot.spring.dao;

import ru.ot.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getQuestionList() throws IOException;
}
