package space.springbok.springaiintro.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.springbok.springaiintro.model.Answer;
import space.springbok.springaiintro.model.Question;
import space.springbok.springaiintro.services.OpenAIService;

/**
 * @author John Spangenberg
 */
@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
}
