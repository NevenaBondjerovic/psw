package com.psw.clinicalcentre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;


@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("%s");
        return message;
    }

}
