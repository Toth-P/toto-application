package domain;

public enum Outcome {

    _1("1"),
    _2("2"),
    X("x");

    private final String outcome;

    Outcome(String s) {
        outcome = s;
    }

    public String getOutcome() {
        return outcome;
    }
}
