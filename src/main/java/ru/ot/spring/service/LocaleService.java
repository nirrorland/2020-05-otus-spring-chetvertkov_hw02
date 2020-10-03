package ru.ot.spring.service;

import org.springframework.stereotype.Service;
import ru.ot.spring.config.YamlConf;
import java.util.Locale;

@Service
public class LocaleService {

    private final YamlConf conf;

    public LocaleService(YamlConf conf) {
        this.conf = conf;
    }

        Locale getLocale() {
            return conf.getLocale() == null ? new Locale("en") : conf.getLocale();
        }

}
