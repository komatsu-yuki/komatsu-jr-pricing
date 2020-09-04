package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.child;

public enum Age {
    ADULT,
    CHILD;

    public boolean isChild() {
        return this.equals(CHILD);
    }
}
