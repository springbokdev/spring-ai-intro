package space.springbok.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * @author John Spangenberg
 */
public record GetCapitalResponse(@JsonPropertyDescription("This is the city name") String answer) {
}
