package ru.ot.spring.dao;

import org.springframework.stereotype.Component;
import ru.ot.spring.domain.Question;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.ot.spring.service.FileIOService;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private final FileIOService fileIOService;

    public QuestionDaoImpl(FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public List<Question> getQuestionList() throws IOException {
        List<Question> resultList = new LinkedList<Question>();

        Reader in = fileIOService.getReader();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(';')
                .parse(in);
        for (CSVRecord record : records) {
            resultList.add(new Question(record.get(0), record.get(1)));
        }

        return resultList;
    }
}
