package service;

import domain.Outcome;
import domain.Round;
import domain.Statistics;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class to handle statistical data.
 */
public class StatisticService {

    public static final int INT_FOR_PERCENTAGE = 100;

    public Statistics calculateStatistics(List<Round> rounds) {
        return Statistics.builder()
                .withFirstWinPercentage(checkOutcomePercentage("1", rounds))
                .withSecondWinPercentag(checkOutcomePercentage("2", rounds))
                .withDrawPercentage(checkOutcomePercentage("X", rounds))
                .build();
    }

    private double checkOutcomePercentage(String outcome, List<Round> rounds) {
        List<String> outcomeList = getStringList(rounds);
        int allOutcomes = outcomeList.size();
        long sumOfOutcome = getSumOfOutcome(outcome, outcomeList);
        return ((double) sumOfOutcome / allOutcomes) * INT_FOR_PERCENTAGE;
    }

    private long getSumOfOutcome(String outcome, List<String> outcomeList) {
        return outcomeList.stream().filter(e -> e.equalsIgnoreCase(outcome)).count();
    }

    private List<String> getStringList(List<Round> rounds) {
        return rounds.stream()
                .flatMap(e -> e.getOutcomes()
                        .stream()
                        .map(Outcome::getOutcome))
                .collect(Collectors.toList());
    }
}
