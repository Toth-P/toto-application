package domain;

import java.time.LocalDate;
import java.util.List;

public class Round {
    private int year;
    private int week;
    private int roundOfWeek;
    private LocalDate date;
    private List<Outcome> outcomes;
    private List<Hit> hits;

    public Round(int year, int week, int roundOfWeek, LocalDate date, List<Outcome> outcomes, List<Hit> hits) {
        this.year = year;
        this.week = week;
        this.roundOfWeek = roundOfWeek;
        this.date = date;
        this.outcomes = outcomes;
        this.hits = hits;
    }

    public Round() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getRoundOfWeek() {
        return roundOfWeek;
    }

    public void setRoundOfWeek(int roundOfWeek) {
        this.roundOfWeek = roundOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

}
