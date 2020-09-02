package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit;

import lombok.Getter;

@Getter
public class FareYen implements FareUnit<FareYen> {
    public static final FareYen ZERO = new FareYen(0);
    private final int value;

    public FareYen(int value) {
        if (value < 0) throw new IllegalArgumentException("料金が負の値です");
        this.value = value / 10 * 10;//10円未満の端数切り捨て
    }

    @Override
    public FareYen plus(FareYen that) {
        return new FareYen(this.value + that.value);
    }

    @Override
    public FareYen minus(FareYen that) {
        return new FareYen(this.value - that.value);
    }

    @Override
    public FareYen times(FareRate fareRate) {
        return new FareYen((int) (value * fareRate.getValue()));
    }

    @Override
    public FareYen times(FareCount fareCount) {
        return new FareYen(value * fareCount.getValue());
    }

    @Override
    public String toString() {
        return value + "円";
    }
}
