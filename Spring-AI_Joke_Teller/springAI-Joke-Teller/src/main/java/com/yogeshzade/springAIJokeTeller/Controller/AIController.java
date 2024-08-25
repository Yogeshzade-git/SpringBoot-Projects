package com.yogeshzade.springAIJokeTeller.Controller;

import com.yogeshzade.springAIJokeTeller.Service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    AIService aiService;

    @GetMapping("/name")
    public String name(){
        return "Yogesh";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/jokes")
    public String getJoke(@RequestParam String topic){
        return aiService.getJoke(topic);
    }

}
