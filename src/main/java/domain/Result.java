package domain;

/**
 * Domain class to contain result related data.
 */
public class Result {
    private final int hitCount;
    private final int prize;

    private Result(Builder builder) {
        this.hitCount = builder.hitCount;
        this.prize = builder.prize;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getPrize() {
        return prize;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int hitCount;
        private int prize;

        private Builder() {
        }

        public Builder withHitCount(int hitCount) {
            this.hitCount = hitCount;
            return this;
        }

        public Builder withPrize(int prize) {
            this.prize = prize;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}

