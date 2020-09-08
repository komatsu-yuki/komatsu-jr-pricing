package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ChildFare implements TotalFare {
    private static final FareRate CHILD_FARE_RATE = new FareRate(0.5);
    private final OneWayFare oneWayFare;


    @Override
    public BasicFareYen getBasicFareYen() {
        BasicFareYen adultYen = oneWayFare.getBasicFareYen();
        return adultYen.times(CHILD_FARE_RATE);
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        SuperExpressSurchargeYen adultYen = oneWayFare.getSuperExpressSurchargeYen();
        return adultYen.times(CHILD_FARE_RATE);
    }
}
