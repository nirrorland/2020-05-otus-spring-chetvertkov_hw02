package ru.ot.spring.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.ot.spring.config.YamlConf;
import ru.ot.spring.dao.QuestionDaoImpl;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
class QuestionDaoImplTest {
    @MockBean
    YamlConf yamlConf;

    @MockBean
    AssessmentService as = null;

    @Autowired
    ApplicationContext context;

    @Autowired
    QuestionDaoImpl questionDao;

    @Test
    public void whenEnThenTextInEnglish() throws IOException {
        Mockito.when(yamlConf.getLocale()).thenReturn(new Locale("en"));

        assertEquals(questionDao.getQuestionList().get(0).getText(), "Which Apollo mission successfully returned to the Earth but did not reach Moon due to accident?\n" +
                "1. Apollo 1\n" +
                "2. Apollo 11\n" +
                "3. Apollo 13");
    }
}