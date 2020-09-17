package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic;

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
public class BasicFareYen implements FareUnit<BasicFareYen> {
    private final FareYen fareYen;

    @Override
    public BasicFareYen plus(BasicFareYen that) {
        return new BasicFareYen(fareYen.plus(that.fareYen));
    }

    @Override
    public BasicFareYen minus(BasicFareYen that) {
        return new BasicFareYen(fareYen.minus(that.fareYen));
    }

    @Override
    public BasicFareYen times(FareRate fareRate) {
        return new BasicFareYen(fareYen.times(fareRate));
    }

    @Override
    public BasicFareYen times(FareCount fareCount) {
        return new BasicFareYen(fareYen.times(fareCount));
    }

    @Override
    public String toString() {
        return "運賃=" + fareYen;
    }
}
