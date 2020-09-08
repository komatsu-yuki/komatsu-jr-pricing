package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurcharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OneWayFareFactory {
    private final BasicFareFactory basicFareFactory;
    private final SuperExpressSurchargeFactory superExpressSurchargeFactory;

    public OneWayFare create(BoardingSection boardingSection,
                             BoardingDate boardingDate,
                             Seat seat,
                             SuperExpressName superExpressName) {
        BasicFare basicFare = basicFareFactory.create(boardingSection);
        SuperExpressSurcharge superExpressSurcharge = superExpressSurchargeFactory.create(boardingSection, boardingDate, seat, superExpressName);

        return new OneWayFare(basicFare.getBasicFareYen(), superExpressSurcharge.calculateSuperExpressSurchargeYen());
    }
}