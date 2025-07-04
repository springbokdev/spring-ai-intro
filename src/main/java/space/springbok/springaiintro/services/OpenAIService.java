package space.springbok.springaiintro.services;

import space.springbok.springaiintro.model.*;

/**
 * @author John Spangenberg
 */
public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);

    GetCapitalResponseWithInfo getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}
