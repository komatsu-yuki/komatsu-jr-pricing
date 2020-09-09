package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChildFare implements TotalFare {
    private final OneWayFare oneWayFare;
    private final ChildDiscount discount;

    @Override
    public BasicFareYen getBasicFareYen() {
        return discount.calculateBasicFareYen(oneWayFare.getBasicFareYen());
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        return discount.calculateSuperExpressSurchargeYen(oneWayFare.getSuperExpressSurchargeYen());
    }
}
