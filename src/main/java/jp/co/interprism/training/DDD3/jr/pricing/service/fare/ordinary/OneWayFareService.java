package jp.co.interprism.training.DDD3.jr.pricing.service.fare.ordinary;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.Age;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.ChildDiscount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.ChildFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OneWayFareService {
    private final BasicFareFactory basicFareFactory;
    private final SuperExpressSurchargeFactory superExpressSurchargeFactory;

    public FareYen calculate(BoardingSection boardingSection,
                             BoardingDate boardingDate,
                             Seat seat,
                             SuperExpressName superExpressName,
                             Age age) {
        OneWayFareFactory oneWayFareFactory = new OneWayFareFactory(basicFareFactory, superExpressSurchargeFactory);
        OneWayFare adultFare = oneWayFareFactory.create(boardingSection, boardingDate, seat, superExpressName);
        TotalFare totalFare = age.isChild() ? new ChildFare(adultFare, new ChildDiscount()) : adultFare;
        return totalFare.sumFareYen();
    }
}
