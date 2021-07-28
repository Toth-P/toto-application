package service;

import domain.Outcome;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InputServiceTest {
    public static final String INPUT = "12x2";
    InputService inputService = new InputService();

    @Test
    public void testGetOutcomeListFromInputShouldReturnSameOutcome() {
        List<Outcome> actual = inputService.getOutcomeListFromInput(INPUT);
        List<Outcome> expected = createExpected();

        assertEquals(expected, actual);
    }

    private List<Outcome> createExpected() {
        List<Outcome> outcomes = new ArrayList<>();
        outcomes.add(Outcome._1);
        outcomes.add(Outcome._2);
        outcomes.add(Outcome.X);
        outcomes.add(Outcome._2);
        return outcomes;
    }
}