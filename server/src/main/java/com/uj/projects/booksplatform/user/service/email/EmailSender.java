package com.uj.projects.booksplatform.user.service.email;

public interface EmailSender {
    boolean SendEmail(String from, String to, String topic, String content);
}
