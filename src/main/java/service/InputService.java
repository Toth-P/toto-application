package service;

import domain.Outcome;

import java.util.ArrayList;
import java.util.List;

public class InputService {

    public List<Outcome> getOutcomeListFromInput(String inputOutcomes) {
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
}
