package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdditionalCharge {
    private final FareYen fareYen;

    public SuperExpressSurchargeYen addTo(SuperExpressSurchargeYen superExpressSurchargeYen) {
        FareYen noAddedFareYen = superExpressSurchargeYen.getFareYen();
        FareYen addedFareYen = noAddedFareYen.plus(fareYen);
        return new SuperExpressSurchargeYen(addedFareYen);
    }
}
