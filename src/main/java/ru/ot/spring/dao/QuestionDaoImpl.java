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

@Component
public class QuestionDaoImpl implements QuestionDao {

    @Override
    public List<Question> getQuestionList(String fileName) throws IOException {
        List<Question> resultList = new LinkedList<Question>();

        Reader in = new FileReader(getClass().getClassLoader().getResource(fileName).getFile());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(';')
                .parse(in);
        for (CSVRecord record : records) {
            resultList.add(new Question(record.get(0), record.get(1)));
        }

        return resultList;
    }
}
