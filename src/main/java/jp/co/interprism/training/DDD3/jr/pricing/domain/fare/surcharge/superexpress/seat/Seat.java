package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat;

public enum Seat {
    RESERVED,
    FREE;

    public boolean isFree() {
        return this.equals(FREE);
    }
}
