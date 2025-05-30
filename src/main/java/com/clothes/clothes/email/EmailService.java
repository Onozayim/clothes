package com.clothes.clothes.email;

import org.thymeleaf.context.Context;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);

    String sendHTMLEmail(EmailDetails details, Context context, String template);
}
