package space.springbok.springaiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.springbok.springaiintro.model.*;

import java.util.Map;
import java.util.Objects;

/**
 * @author John Spangenberg
 */
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    @Autowired
    ObjectMapper objectMapper;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalWithInfo;


    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getText();
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
        "format", format));
        ChatResponse response = chatModel.call(prompt);

        return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
    }

    @Override
    public GetCapitalResponseWithInfo getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {

        BeanOutputConverter<GetCapitalResponseWithInfo> converter = new BeanOutputConverter<>(GetCapitalResponseWithInfo.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                "format", format));
        ChatResponse response = chatModel.call(prompt);

        return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
    }
}
