package domain;

/**
 * Domain class to contain outcome related data.
 */
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
