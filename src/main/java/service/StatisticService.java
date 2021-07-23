package service;

import domain.Outcome;
import domain.Round;
import domain.Statistics;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticService {
    public Statistics calculateStatistics(List<Round> rounds) {
        return new Statistics(outcomePercentage("1", rounds), outcomePercentage("2", rounds), outcomePercentage("X", rounds));
    }

    private double outcomePercentage(String outcome, List<Round> rounds) {
        List<String> outcomeList = getStringList(rounds);
        int allOutcomes = outcomeList.size();
        long sumOfOutcome = outcomeList.stream().filter(e -> e.equalsIgnoreCase(outcome)).count();
        return ((double) sumOfOutcome / allOutcomes) * 100;
    }

    private List<String> getStringList(List<Round> rounds) {
        return rounds.stream().flatMap(e -> e.getOutcomes().stream().map(Outcome::getOutcome)).collect(Collectors.toList());
    }
}
