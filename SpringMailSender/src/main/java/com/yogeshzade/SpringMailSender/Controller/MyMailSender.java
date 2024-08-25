package com.yogeshzade.SpringMailSender.Controller;

import com.yogeshzade.SpringMailSender.DTO.EmailRequest;
import com.yogeshzade.SpringMailSender.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MyMailSender {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send-mail")
    public ResponseEntity <String> sendMail(@RequestBody EmailRequest emailRequest){
        mailSenderService.sendMail(emailRequest);
        return new ResponseEntity<>("Email send Success", HttpStatus.OK);
    }

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendmail(@RequestParam("to") String to,
                         @RequestParam("subject") String subject,
                         @RequestParam("mailBody") String mailBody,
                         @RequestParam(value = "attachment", required = false) List<MultipartFile> attachment){

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(to);
        emailRequest.setSubject(subject);
        emailRequest.setMailBody(mailBody);
        emailRequest.setAttachment(attachment);

        mailSenderService.sendMail(emailRequest);

        return new ResponseEntity<>("Email has been send Successfully", HttpStatus.OK);
    }

    @GetMapping("/")
    public String greet(){
        return "Hello Buddy";
    }

}
