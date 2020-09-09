package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.Discount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;

public class ChildDiscount implements Discount {
    private static final FareRate CHILD_FARE_RATE = new FareRate(0.5);

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public BasicFareYen calculateBasicFareYen(BasicFareYen basicFareYen) {
        return basicFareYen.times(CHILD_FARE_RATE);
    }

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYen(SuperExpressSurchargeYen superExpressSurchargeYen) {
        return superExpressSurchargeYen.times(CHILD_FARE_RATE);
    }
}
