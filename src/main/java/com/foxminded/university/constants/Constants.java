package com.foxminded.university.constants;

import java.time.format.DateTimeFormatter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Constants {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final ClassPathXmlApplicationContext CONTEXT_SPRING=new ClassPathXmlApplicationContext("appContextSpring.xml");
    private Constants() {
    }
}
