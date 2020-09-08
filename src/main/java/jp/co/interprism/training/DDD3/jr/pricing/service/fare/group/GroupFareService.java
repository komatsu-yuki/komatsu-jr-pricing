package jp.co.interprism.training.DDD3.jr.pricing.service.fare.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.Group;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.GroupDiscount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.GroupFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupFareService {
    private final BasicFareFactory basicFareFactory;
    private final SuperExpressSurchargeFactory superExpressSurchargeFactory;

    public FareYen calculate(BoardingSection boardingSection,
                             BoardingDate boardingDate,
                             Seat seat,
                             SuperExpressName superExpressName,
                             AdultsCount adultsCount,
                             ChildrenCount childrenCount) {
        Group group = new Group(adultsCount, childrenCount);
        GroupDiscount groupDiscount = new GroupDiscount(group, boardingDate);

        OneWayFareFactory oneWayFareFactory = new OneWayFareFactory(basicFareFactory, superExpressSurchargeFactory);
        OneWayFare oneWayFare = oneWayFareFactory.create(boardingSection, boardingDate, seat, superExpressName);

        GroupFare groupFare = new GroupFare(oneWayFare, groupDiscount);
        return groupFare.sumFareYen();
    }
}
