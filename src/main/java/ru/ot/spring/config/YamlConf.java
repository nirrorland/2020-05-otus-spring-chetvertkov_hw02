package ru.ot.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class YamlConf {
    private Integer minscore;
    private Locale locale;

    public Integer getMinscore() {
        return minscore;
    }

    public void setMinscore(Integer minscore) {
        this.minscore = minscore;
    }

    public Locale getLocale() {
        return this.locale ;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
