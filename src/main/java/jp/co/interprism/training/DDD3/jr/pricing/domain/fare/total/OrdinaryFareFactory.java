package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total;

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
public class OrdinaryFareFactory {
    private final BasicFareFactory basicFareFactory;
    private final SuperExpressSurchargeFactory superExpressSurchargeFactory;

    public OrdinaryFare create(BoardingSection boardingSection,
                            BoardingDate boardingDate,
                            Seat seat,
                            SuperExpressName superExpressName) {
        BasicFare basicFare = basicFareFactory.create(boardingSection);
        SuperExpressSurcharge superExpressSurcharge = superExpressSurchargeFactory.create(boardingSection, boardingDate, seat, superExpressName);

        return new OrdinaryFare(basicFare.getBasicFareYen(), superExpressSurcharge.calculateSuperExpressSurchargeYen());
    }
}