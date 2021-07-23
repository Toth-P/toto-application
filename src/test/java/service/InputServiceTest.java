package service;

import domain.Outcome;
import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InputServiceTest {
    InputService inputService = new InputService();


    @Test
    public void testGetOutcomeListFromInputShouldReturnSameOutcome() {
        String input = "12x2";
        List<Outcome> actual = inputService.getOutcomeListFromInput(input);
        List<Outcome> expected = getMockOutcomeList();

        assertEquals( expected,actual);
    }

    private List<Outcome> getMockOutcomeList() {
        List<Outcome> mockList = new ArrayList<>();
        mockList.add(Outcome._1);
        mockList.add(Outcome._2);
        mockList.add(Outcome.X);
        mockList.add(Outcome._2);
        return mockList;
    }
}