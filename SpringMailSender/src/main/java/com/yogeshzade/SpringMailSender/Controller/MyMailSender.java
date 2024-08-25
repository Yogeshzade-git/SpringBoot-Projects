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
@RequestMapping("/api")
public class MyMailSender {
    @Autowired
    private MailSenderService mailSenderService;

//    @PostMapping("/send-mail")
//    public ResponseEntity <String> sendMail(@RequestBody EmailRequest emailRequest){
//        mailSenderService.sendMail(emailRequest);
//        return new ResponseEntity<>("Email send Success", HttpStatus.OK);
//    }

    @PostMapping("/send-mail")
    public ResponseEntity<String> sendmail(@RequestParam("to") String to,
                         @RequestParam("subject") String subject,
                         @RequestParam("mailBody") String mailBody,
                         @RequestParam(value = "attachment", required = false) List<MultipartFile> attachment){

                    EmailRequest emailRequest = new EmailRequest();
                    emailRequest.setTo(to);
                    emailRequest.setSubject(subject);
                    emailRequest.setMailBody(mailBody);
                    emailRequest.setAttachment(attachment);

        try {
            mailSenderService.sendMail(emailRequest);
            return ResponseEntity.ok("Email sent successfully!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email!");
        }
    }

    @GetMapping("/")
    public String greet(){
        return "Hello Buddy";
    }

}
