package ru.ot.spring.service;

import org.springframework.stereotype.Service;
import ru.ot.spring.config.YamlConf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@Service
public class FileIOService {

    private final YamlConf conf;

    public FileIOService(YamlConf conf) {
        this.conf = conf;
    }

    private String getFileName() {
            String filename = conf.getLocale() == null ? "en" : conf.getLocale().getLanguage();
            return filename + ".csv";
    }

    public Reader getReader() throws FileNotFoundException {
        return new FileReader(getClass().getClassLoader().getResource(getFileName()).getFile());
    }

}
