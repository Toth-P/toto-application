package service;

import domain.Hit;
import domain.Outcome;
import domain.Result;
import domain.Round;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ResultServiceTest {
    ResultService resultService = new ResultService();


    @Test
    public void testGetResultShouldReturnSameResult() {
        List<Outcome> outcomeList1 = mockInputOutcomes();
        Result actual = resultService.getResult(getMockRounds(), LocalDate.parse("2015-10-29"), outcomeList1);

        assertEquals(4,actual.getHitCount());
        assertEquals(500,actual.getPrize());
    }

    @Test(expected = DateTimeParseException.class)
    public void testGetResultShouldThrownDateTimeParseException() {
        List<Outcome> outcomeList1 = mockInputOutcomes();
        resultService.getResult(getMockRounds(), LocalDate.parse("2015-99-99"), outcomeList1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetResultShouldThrownNoSuchElementException() {
        List<Outcome> outcomeList1 = mockInputOutcomes();
        resultService.getResult(getMockRounds(), LocalDate.parse("3000-01-01"), outcomeList1);
    }

    private List<Outcome> mockInputOutcomes() {
        List<Outcome> outcomeList1 = new ArrayList<>();
        outcomeList1.add(Outcome._1);
        outcomeList1.add(Outcome._2);
        outcomeList1.add(Outcome.X);
        outcomeList1.add(Outcome._1);
        return outcomeList1;
    }
    public List<Round> getMockRounds() {
        List<Outcome> outcomeList1 = new ArrayList<>();
        outcomeList1.add(Outcome._1);
        outcomeList1.add(Outcome._2);
        outcomeList1.add(Outcome.X);
        outcomeList1.add(Outcome._1);

        List<Hit> hitList1 = new ArrayList<>();
        hitList1.add(Hit.builder().withHitCount(4).withPrize(500).withNumberOfWagers(5).build());

        List<Hit> hitList2 = new ArrayList<>();
        hitList2.add(Hit.builder().withHitCount(14).withPrize(800).withNumberOfWagers(5).build());

        List<Round> rounds = new ArrayList<>();
        Round round1 = new Round(2015, 44, 1, LocalDate.parse("2015-10-29"), outcomeList1, hitList1);
        Round round2 = new Round(2015, 44, 1, LocalDate.parse("2015-10-28"), outcomeList1, hitList2);

        rounds.add(round1);
        rounds.add(round2);
        return rounds;
    }

}