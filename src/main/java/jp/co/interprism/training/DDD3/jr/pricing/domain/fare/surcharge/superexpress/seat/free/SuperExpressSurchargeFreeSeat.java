package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.free;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurcharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuperExpressSurchargeFreeSeat implements SuperExpressSurcharge {
    private static final FareYen FREE_SEAT_DISCOUNT = new FareYen(530);
    private final SuperExpressSurchargeYen superExpressSurchargeYen;

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYen() {
        FareYen fareYen = superExpressSurchargeYen.getFareYen();
        FareYen freeSeatFareYen = fareYen.minus(FREE_SEAT_DISCOUNT);
        return new SuperExpressSurchargeYen(freeSeatFareYen);
    }
}
