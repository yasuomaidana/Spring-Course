package com.JavaSpringPractice.ReditClone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@AllArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;
    String build(String messate){
        Context context = new Context();
        context.setVariable("message",context);
        return  templateEngine.process("mailTemplate",context);
    }
}
