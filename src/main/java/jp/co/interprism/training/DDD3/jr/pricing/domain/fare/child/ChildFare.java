package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.child;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.OrdinaryFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ChildFare implements TotalFare {
    private static final FareRate CHILD_FARE_RATE = new FareRate(0.5);
    private final OrdinaryFare ordinaryFare;


    @Override
    public BasicFareYen getBasicFareYen() {
        BasicFareYen adultYen = ordinaryFare.getBasicFareYen();
        return adultYen.times(CHILD_FARE_RATE);
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        SuperExpressSurchargeYen adultYen = ordinaryFare.getSuperExpressSurchargeYen();
        return adultYen.times(CHILD_FARE_RATE);
    }
}
