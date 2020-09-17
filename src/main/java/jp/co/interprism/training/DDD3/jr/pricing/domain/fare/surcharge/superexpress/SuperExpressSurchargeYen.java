package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareUnit;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class SuperExpressSurchargeYen implements FareUnit<SuperExpressSurchargeYen> {
    private final FareYen fareYen;

    @Override
    public SuperExpressSurchargeYen plus(SuperExpressSurchargeYen that) {
        return new SuperExpressSurchargeYen(fareYen.plus(that.fareYen));
    }

    @Override
    public SuperExpressSurchargeYen minus(SuperExpressSurchargeYen that) {
        return new SuperExpressSurchargeYen(fareYen.minus(that.fareYen));
    }

    @Override
    public SuperExpressSurchargeYen times(FareRate fareRate) {
        return new SuperExpressSurchargeYen(fareYen.times(fareRate));
    }

    @Override
    public SuperExpressSurchargeYen times(FareCount fareCount) {
        return new SuperExpressSurchargeYen(fareYen.times(fareCount));
    }

    @Override
    public String toString() {
        return "特急料金=" + fareYen;
    }
}
