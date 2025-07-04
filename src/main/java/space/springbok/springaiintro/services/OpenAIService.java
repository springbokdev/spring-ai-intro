package space.springbok.springaiintro.services;

import space.springbok.springaiintro.model.Answer;
import space.springbok.springaiintro.model.GetCapitalRequest;
import space.springbok.springaiintro.model.Question;

/**
 * @author John Spangenberg
 */
public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}
