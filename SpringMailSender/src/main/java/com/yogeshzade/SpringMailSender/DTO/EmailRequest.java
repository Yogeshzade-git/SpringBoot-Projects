package com.yogeshzade.SpringMailSender.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class EmailRequest {

    private String to;
    private String subject;
    private String mailBody;
    private List<MultipartFile> attachment;
}
