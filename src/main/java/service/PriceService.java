package service;

import domain.Hit;
import domain.Round;

import java.util.List;
import java.util.stream.Collectors;

public class PriceService {
    public int getLargestPrize(List<Round> rounds) {
        List<Hit> hitList = rounds.stream().flatMap(e -> e.getHits().stream()).collect(Collectors.toList());
        List<Integer> hits = hitList.stream().map(Hit::getPrize).collect(Collectors.toList());
        return hits.stream().mapToInt(e -> e).max().orElseThrow();
    }
}
