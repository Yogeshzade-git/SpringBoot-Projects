package com.yogeshzade.SpringMailSender.Service;

import com.yogeshzade.SpringMailSender.DTO.EmailRequest;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MailSenderService {
    @Autowired
    JavaMailSender javaMailSender;


    public void sendMail(EmailRequest emailRequest) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getMailBody(), true);

            List<MultipartFile> attachments = emailRequest.getAttachment();

            if(attachments != null && !attachments.isEmpty()){
                for(MultipartFile file : attachments) {
                    if (!file.isEmpty()) {
                        helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
                    }
                }
            }

            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.error("Error while sending mail: ", e);
        }
    }
}
