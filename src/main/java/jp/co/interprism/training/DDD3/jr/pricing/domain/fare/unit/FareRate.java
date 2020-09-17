package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class FareRate {
    private final double value;
}
