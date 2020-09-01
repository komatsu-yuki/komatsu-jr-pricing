package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit;

import lombok.Getter;

@Getter
public class FareCount implements Comparable<FareCount> {
    public static final FareCount ZERO = new FareCount(0);

    private final int value;

    public FareCount(int value) {
        if (value < 0) throw new IllegalArgumentException("個数が負の値です");
        this.value = value;
    }

    public FareCount plus(FareCount that) {
        return new FareCount(value + that.getValue());
    }

    public FareCount minus(FareCount that) {
        return new FareCount(value - that.getValue());
    }

    @Override
    public int compareTo(FareCount that) {
        return this.value - that.value;
    }
}
