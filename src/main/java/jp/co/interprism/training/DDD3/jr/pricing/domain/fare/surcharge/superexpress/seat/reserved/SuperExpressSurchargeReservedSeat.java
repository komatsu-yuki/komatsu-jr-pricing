package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.Season;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurcharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuperExpressSurchargeReservedSeat implements SuperExpressSurcharge {
    private final SuperExpressSurchargeYen superExpressSurchargeYen;
    private final AdditionalCharge additionalCharge;
    private final SuperExpressName superExpressName;
    private final Season season;

    private SuperExpressSurchargeYen changeForSeason() {
        SuperExpressSurchargeYen change = new SuperExpressSurchargeYen(new FareYen(200));
        if (season.isPeak()) return superExpressSurchargeYen.plus(change);
        if (season.isOffPeak()) return superExpressSurchargeYen.minus(change);
        return superExpressSurchargeYen;
    }

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYen() {
        if (superExpressName.hasAdditionalCharge())
            return additionalCharge.addTo(changeForSeason());
        return changeForSeason();
    }
}
