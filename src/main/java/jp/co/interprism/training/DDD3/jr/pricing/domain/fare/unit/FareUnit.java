package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit;

public interface FareUnit<T> {
    T plus(T that);

    T minus(T that);

    T times(FareRate fareRate);

    T times(FareCount fareCount);
}
