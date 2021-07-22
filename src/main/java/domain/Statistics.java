package domain;

import java.text.DecimalFormat;

public class Statistics {
    private double firstWinPercentage;
    private double secondWinPercentage;
    private double drawPercentage;

    public Statistics(double firstWinPercentage, double secondWinPercentage, double drawPercentage) {
        this.firstWinPercentage = firstWinPercentage;
        this.secondWinPercentage = secondWinPercentage;
        this.drawPercentage = drawPercentage;
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

    DecimalFormat df = new DecimalFormat("###.##");

    @Override
    public String toString() {
        return "Statistics:" +
                " team #1 won: " + df.format(getFirstWinPercentage()) + " %" +
                ", team #2 won: " + df.format(getSecondWinPercentage()) + " %" +
                ",  draw: " + df.format(getDrawPercentage()) + " %";
    }


}
