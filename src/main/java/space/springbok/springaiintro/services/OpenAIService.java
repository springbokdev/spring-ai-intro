package space.springbok.springaiintro.services;

import space.springbok.springaiintro.model.Answer;
import space.springbok.springaiintro.model.Question;

/**
 * @author John Spangenberg
 */
public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);
}
