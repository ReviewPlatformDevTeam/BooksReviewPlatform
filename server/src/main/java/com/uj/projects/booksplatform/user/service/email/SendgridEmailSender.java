package com.uj.projects.booksplatform.user.service.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendgridEmailSender implements EmailSender {

    private static final String SEND_GRID_KEY_NAME = "SENDGRID_API_KEY";

    @Override
    public void SendEmail(String from, String to, String topic, String content) {
        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(fromEmail, topic, toEmail, emailContent);

        Dotenv dotenv = Dotenv.load();
        String sendGridKey = dotenv.get(SEND_GRID_KEY_NAME);

        SendGrid sendGridClient = new SendGrid(sendGridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGridClient.api(request);
        } catch (IOException ex) {
            System.out.println("Exception during sending email: " + ex);
        }
    }
}
