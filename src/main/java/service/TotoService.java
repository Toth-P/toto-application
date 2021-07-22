package service;

import data.DataStore;
import data.FileBasedDataStore;
import domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class TotoService implements DataStore {

    private DataStore dataStore;


    @Override
    public List<Round> getRounds() {
        FileBasedDataStore fileBasedDataStore = new FileBasedDataStore();
        return fileBasedDataStore.getRounds();
    }

    public int getLargestPrize() {
        List<Hit> hitList = getRounds().stream().flatMap(e -> e.getHits().stream()).collect(Collectors.toList());
        List<Integer> hits = hitList.stream().map(Hit::getPrize).collect(Collectors.toList());
        return hits.stream().mapToInt(e -> e).max().orElseThrow();
    }

    public Statistics calculateStatistics() {
        return new Statistics(outcomePercentage("1"), outcomePercentage("2"), outcomePercentage("X"));
    }

    private double outcomePercentage(String outcome) {
        List<String> outcomeList = getStringList();
        int allOutcomes = outcomeList.size();
        long sumOfOutcome = outcomeList.stream().filter(e -> e.equalsIgnoreCase(outcome)).count();
        return ((double) sumOfOutcome / allOutcomes) * 100;
    }

    private List<String> getStringList() {
        return getRounds().stream().flatMap(e -> e.getOutcomes().stream().map(Outcome::getOutcome)).collect(Collectors.toList());
    }

    public List<Outcome> getOutcomeList(String inputOutcomes) {
        List<String> outcomeStringList = new ArrayList<>();

        for (int i = 0; i < inputOutcomes.length(); i++) {
            outcomeStringList.add(Character.toString(inputOutcomes.charAt(i)));
        }
        return getOutcomeList(outcomeStringList);
    }

    private List<Outcome> getOutcomeList(List<String> outcomeStringList) {
        List<Outcome> outcomeList = new ArrayList<>();
        for (String s : outcomeStringList) {
            if (s.equalsIgnoreCase("1"))
                outcomeList.add(Outcome._1);
            else if (s.equalsIgnoreCase("2")) {
                outcomeList.add(Outcome._2);
            } else if (s.equalsIgnoreCase("X"))
                outcomeList.add(Outcome.X);
        }
        return outcomeList;
    }

    public Result getResult(LocalDate localDate, List<Outcome> outcomeList) {
        Optional<Round> round = getRounds().stream().filter(e -> e.getDate().equals(localDate)).findAny();
        List<Outcome> outcomes = round.get().getOutcomes();
        List<Hit> hitList = round.get().getHits();

        int totalHits = getTotalHits(outcomeList, outcomes);
        int prize = getPrize(hitList, totalHits);

        return new Result(totalHits, prize);

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
