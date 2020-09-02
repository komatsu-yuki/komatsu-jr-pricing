package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.Season;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurcharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuperExpressSurchargeReservedSeat implements SuperExpressSurcharge {
    private static final SuperExpressSurchargeYen CHANGE = new SuperExpressSurchargeYen(new FareYen(200));

    private final SuperExpressSurchargeYen superExpressSurchargeYen;
    private final AdditionalCharge additionalCharge;
    private final SuperExpressName superExpressName;
    private final Season season;

    private SuperExpressSurchargeYen changeForSeason() {

        if (season.isPeak()) return superExpressSurchargeYen.plus(CHANGE);
        if (season.isOffPeak()) return superExpressSurchargeYen.minus(CHANGE);
        return superExpressSurchargeYen;
    }

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYen() {
        if (superExpressName.hasAdditionalCharge())
            return additionalCharge.addTo(changeForSeason());
        return changeForSeason();
    }
}
