package domain;

/**
 * Domain class to contain hit related data.
 */
public class Hit {

    private final int hitCount;
    private final int numberOfWagers;
    private final int prize;

    private Hit(Builder builder) {
        this.hitCount = builder.hitCount;
        this.numberOfWagers = builder.numberOfWagers;
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
        private int numberOfWagers;
        private int prize;

        private Builder() {
        }

        public Builder withHitCount(int hitCount) {
            this.hitCount = hitCount;
            return this;
        }

        public Builder withNumberOfWagers(int numberOfWagers) {
            this.numberOfWagers = numberOfWagers;
            return this;
        }

        public Builder withPrize(int prize) {
            this.prize = prize;
            return this;
        }

        public Hit build() {
            return new Hit(this);
        }
    }
}
