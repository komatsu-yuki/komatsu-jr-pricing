package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.round.trip;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.OperatingKilometer;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.Discount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundTripDiscount implements Discount {
    private static final OperatingKilometer ONE_WAY_LOWER_BOUND = new OperatingKilometer(601.0);
    private static final FareRate DISCOUNT_BASIC_FARE_RATE = new FareRate(0.9);
    private static final FareCount ROUND_TRIP_COUNT = new FareCount(2);

    private final OperatingKilometer oneWayOperatingKilometer;

    @Override
    public boolean isAvailable() {
        return oneWayOperatingKilometer.compareTo(ONE_WAY_LOWER_BOUND) >= 0;
    }

    @Override
    public BasicFareYen calculateBasicFareYen(BasicFareYen basicFareYen) {
        return calculateBasicFareYenForOneTime(basicFareYen).times(ROUND_TRIP_COUNT);
    }

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYen(SuperExpressSurchargeYen superExpressSurchargeYen) {
        return calculateSuperExpressSurchargeYenForOneTime(superExpressSurchargeYen).times(ROUND_TRIP_COUNT);
    }

    @Override
    public BasicFareYen calculateBasicFareYenForOneTime(BasicFareYen basicFareYen) {
        return basicFareYen.times(DISCOUNT_BASIC_FARE_RATE);
    }

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYenForOneTime(SuperExpressSurchargeYen superExpressSurchargeYen) {
        return superExpressSurchargeYen;
    }
}
