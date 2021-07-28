package service;

import domain.Hit;
import domain.Round;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PriceServiceTest {
    PriceService priceService = new PriceService();

    @Test
    public void testGetLargestPrizesShouldReturn800WhenTheLargestPrizeIs800() {
        List<Round> rounds = getActualRounds();
        int actual = priceService.getLargestPrize(rounds);
        assertEquals(800, actual);
    }

    public List<Round> getActualRounds() {
        List<Hit> hitList1 = new ArrayList<>();
        hitList1.add(Hit.builder().withHitCount(4).withPrize(500).withNumberOfWagers(5).build());
        List<Hit> hitList2 = new ArrayList<>();
        hitList2.add(Hit.builder().withHitCount(14).withPrize(800).withNumberOfWagers(5).build());

        List<Round> rounds = new ArrayList<>();
        Round round1 = Round.builder()
                .withHits(hitList1)
                .build();
        Round round2 = Round.builder()
                .withHits(hitList2)
                .build();

        rounds.add(round1);
        rounds.add(round2);
        return rounds;
    }
}
