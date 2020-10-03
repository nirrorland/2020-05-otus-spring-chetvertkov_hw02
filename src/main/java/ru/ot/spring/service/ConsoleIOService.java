package ru.ot.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService {
    private final PrintStream out;
    private final Scanner sc;
    private final MessageSource ms;
    private final LocaleService ls;

    public ConsoleIOService(@Value("#{ T(java.lang.System).in}") InputStream in,
                            @Value("#{ T(java.lang.System).out}") PrintStream out,
                            MessageSource ms,
                            LocaleService ls
    ) {
        this.sc = new Scanner(in);
        this.out = out;
        this.ms = ms;
        this.ls = ls;
    }

    public void out(String message) {out.println(message);}
    public void bundleOut(String message) {out.println(ms.getMessage(message, null, ls.getLocale())); }
    public void bundleOut(String message, String[] values) {out.println(ms.getMessage(message, values, ls.getLocale())); }
    public String read() { return sc.nextLine();}

}
