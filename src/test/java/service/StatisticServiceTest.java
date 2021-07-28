package service;

import domain.Outcome;
import domain.Round;
import domain.Statistics;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticServiceTest {
    StatisticService statisticService = new StatisticService();


    @Test
    public void testCalculateStatisticsShouldReturnSameStatistic() {
        Statistics actual = statisticService.calculateStatistics(getActualRounds());
        assertEquals(50.0, actual.getFirstWinPercentage(), 0.001);
        assertEquals(25.0, actual.getSecondWinPercentage(), 0.001);
        assertEquals(25.0, actual.getDrawPercentage(), 0.001);
    }

    public List<Round> getActualRounds() {
        List<Outcome> outcomeList1 = new ArrayList<>();
        outcomeList1.add(Outcome._1);
        outcomeList1.add(Outcome._1);
        outcomeList1.add(Outcome._2);
        outcomeList1.add(Outcome.X);

        List<Round> rounds = new ArrayList<>();
        Round round1 = Round.builder().withOutcomes(outcomeList1).build();

        rounds.add(round1);
        return rounds;
    }
}