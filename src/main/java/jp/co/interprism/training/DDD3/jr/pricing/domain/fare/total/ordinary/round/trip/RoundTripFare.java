package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundTripFare implements TotalFare {
    private static final FareCount ROUND_TRIP_COUNT = new FareCount(2);

    private final TotalFare totalFare;
    private final RoundTripDiscount discount;

    @Override
    public BasicFareYen getBasicFareYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引が適用できません");
        BasicFareYen oneWayYen = discount.calculateBasicFareYen(totalFare.getBasicFareYen());
        return oneWayYen.times(ROUND_TRIP_COUNT);
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引が適用できません");
        SuperExpressSurchargeYen oneWayYen = discount.calculateSuperExpressSurchargeYen(totalFare.getSuperExpressSurchargeYen());
        return oneWayYen.times(ROUND_TRIP_COUNT);
    }
}
