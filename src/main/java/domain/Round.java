package domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Domain class to contain round related data.
 */
public class Round {
    private int year;
    private int week;
    private int roundOfWeek;
    private LocalDate date;
    private List<Outcome> outcomes;
    private List<Hit> hits;

    private Round(Builder builder) {
        this.year = builder.year;
        this.week = builder.week;
        this.roundOfWeek = builder.roundOfWeek;
        this.date = builder.date;
        this.outcomes = builder.outcomes;
        this.hits = builder.hits;
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


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int year;
        private int week;
        private int roundOfWeek;
        private LocalDate date;
        private List<Outcome> outcomes;
        private List<Hit> hits;

        private Builder() {
        }

        public Builder withYear(int year) {
            this.year = year;
            return this;
        }

        public Builder withWeek(int week) {
            this.week = week;
            return this;
        }

        public Builder withRoundOfWeek(int roundOfWeek) {
            this.roundOfWeek = roundOfWeek;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withOutcomes(List<Outcome> outcomes) {
            this.outcomes = outcomes;
            return this;
        }

        public Builder withHits(List<Hit> hits) {
            this.hits = hits;
            return this;
        }

        public Round build() {
            return new Round(this);
        }
    }
}
