package service;

import domain.Hit;
import domain.Outcome;
import domain.Result;
import domain.Round;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class to handle result data.
 */
public class ResultService {
    public Result getResult(List<Round> rounds, LocalDate localDate, List<Outcome> outcomeList) {
        Optional<Round> round = rounds.stream().filter(e -> e.getDate().equals(localDate)).findAny();
        List<Outcome> outcomes = round.get().getOutcomes();
        List<Hit> hitList = round.get().getHits();

        int totalHits = getTotalHits(outcomeList, outcomes);
        int prize = getPrize(hitList, totalHits);

        return Result.builder()
                .withHitCount(totalHits)
                .withPrize(prize)
                .build();
    }

    private int getPrize(List<Hit> hitList, int totalHits) {
        int prize = 0;

        for (Hit hit : hitList) {
            if (totalHits == hit.getHitCount()) {
                prize = hit.getPrize();
            }
        }
        return prize;
    }

    private int getTotalHits(List<Outcome> outcomeList, List<Outcome> outcomes) {
        int totalHits = 0;
        for (int i = 0; i < outcomes.size(); i++) {
            if (outcomes.get(i).equals(outcomeList.get(i))) {
                totalHits++;
            }
        }
        return totalHits;
    }
}
