package jp.co.interprism.training.DDD3.jr.pricing.service.fare.ordinary;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.OperatingKilometer;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.Age;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.ChildDiscount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.ChildFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip.RoundTripDiscount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip.RoundTripFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoundTripFareService {
    private final BasicFareFactory basicFareFactory;
    private final SuperExpressSurchargeFactory superExpressSurchargeFactory;

    public FareYen calculate(BoardingSection boardingSection,
                             BoardingDate boardingDate,
                             Seat seat,
                             SuperExpressName superExpressName,
                             Age age) {
        OperatingKilometer sectionOperatingKilometer = boardingSection.calculateOperatingKilometer();
        RoundTripDiscount discount = new RoundTripDiscount(sectionOperatingKilometer);

        OneWayFareFactory oneWayFareFactory = new OneWayFareFactory(basicFareFactory, superExpressSurchargeFactory);
        OneWayFare adultFare = oneWayFareFactory.create(boardingSection, boardingDate, seat, superExpressName);
        TotalFare oneWayFare = age.isChild() ? new ChildFare(adultFare, new ChildDiscount()) : adultFare;

        RoundTripFare roundTripFare = new RoundTripFare(oneWayFare, discount);
        return roundTripFare.sumFareYen();
    }
}
