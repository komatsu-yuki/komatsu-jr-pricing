package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiscountedFare implements TotalFare {
    private final TotalFare totalFare;
    private final Discount discount;

    @Override
    public BasicFareYen getBasicFareYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引条件を満たしてません");
        return discount.calculateBasicFareYen(totalFare.getBasicFareYen());
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引条件を満たしてません");
        return discount.calculateSuperExpressSurchargeYen(totalFare.getSuperExpressSurchargeYen());
    }
}
