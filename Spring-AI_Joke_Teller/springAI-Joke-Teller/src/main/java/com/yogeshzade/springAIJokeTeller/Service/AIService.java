package com.yogeshzade.springAIJokeTeller.Service;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    @Autowired
    AiClient aiClient;

    public String getJoke(String topic){
        PromptTemplate promptTemplate = new PromptTemplate("Hey consider yourself as a funny person and give me a 1 or 2 line joke on thi topic {topic}? give oly joke in return rather than sure here's a car-related joke for you:");
        promptTemplate.add("topic", topic);
        return aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }
}
