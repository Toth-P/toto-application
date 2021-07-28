package domain;

import java.text.DecimalFormat;

/**
 * Domain class to contain statistic related data.
 */
public class Statistics {
    public static final String PATTERN = "###.##";
    DecimalFormat df = new DecimalFormat(PATTERN);
    private final double firstWinPercentage;
    private final double secondWinPercentage;
    private final double drawPercentage;


    private Statistics(Builder builder) {
        this.firstWinPercentage = builder.firstWinPercentage;
        this.secondWinPercentage = builder.secondWinPercentage;
        this.drawPercentage = builder.drawPercentage;
    }

    public double getFirstWinPercentage() {
        return firstWinPercentage;
    }

    public double getSecondWinPercentage() {
        return secondWinPercentage;
    }

    public double getDrawPercentage() {
        return drawPercentage;
    }


    @Override
    public String toString() {
        return "Statistics:" +
                " team #1 won: " + df.format(getFirstWinPercentage()) + " %" +
                ", team #2 won: " + df.format(getSecondWinPercentage()) + " %" +
                ",  draw: " + df.format(getDrawPercentage()) + " %";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private double firstWinPercentage;
        private double secondWinPercentage;
        private double drawPercentage;

        private Builder() {
        }

        public Builder withFirstWinPercentage(double firstWinPercentage) {
            this.firstWinPercentage = firstWinPercentage;
            return this;
        }

        public Builder withSecondWinPercentag(double secondWinPercentage) {
            this.secondWinPercentage = secondWinPercentage;
            return this;
        }

        public Builder withDrawPercentage(double drawPercentage) {
            this.drawPercentage = drawPercentage;
            return this;
        }

        public Statistics build() {
            return new Statistics(this);
        }
    }
}
