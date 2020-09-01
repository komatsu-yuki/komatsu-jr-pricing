package jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date;

public enum Season {
    REGULAR,
    OFF_PEAK,
    PEAK;

    public boolean isPeak() {
        return this.equals(PEAK);
    }

    public boolean isOffPeak() {
        return this.equals(OFF_PEAK);
    }
}
