package com.example.avisutilisateurs.securite;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class ConfigurationSecuriteApplication {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Bean
    public JavaMailSender javaMailSender() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(props);
        JavaMailSenderImpl send = new JavaMailSenderImpl();
                send.setJavaMailProperties(props);
                send.setSession(session);
        return send ;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize-> authorize
                                .requestMatchers(POST,"/inscription").permitAll()
                                .requestMatchers(POST,"/activation").permitAll()
                                .anyRequest().authenticated()
                ).build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
