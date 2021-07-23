package service;

import domain.Hit;
import domain.Outcome;
import domain.Round;
import domain.Statistics;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticServiceTest {
    StatisticService statisticService = new StatisticService();


    @Test
    public void testCalculateStatisticsShouldReturnSameStatistic() {
        Statistics actual = statisticService.calculateStatistics(getMockRounds());
        assertEquals(25.0, actual.getDrawPercentage(), 0.001);
        assertEquals(50.0, actual.getFirstWinPercentage(), 0.001);
        assertEquals(25.0, actual.getSecondWinPercentage(), 0.001);
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