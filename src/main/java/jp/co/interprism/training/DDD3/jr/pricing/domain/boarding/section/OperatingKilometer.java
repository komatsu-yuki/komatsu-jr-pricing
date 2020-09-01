package jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section;

public class OperatingKilometer implements Comparable<OperatingKilometer> {
    private final double value;

    public OperatingKilometer(double value) {
        this.value = Math.round(value * 10) * 0.1;//0.1km未満は四捨五入
    }

    public OperatingKilometer minus(OperatingKilometer that) {
        return new OperatingKilometer(this.value - that.value);
    }

    @Override
    public int compareTo(OperatingKilometer that) {
        double diff = this.value - that.value;
        if (diff == 0) return 0;
        return diff < 0 ? -1 : 1;
    }
}
