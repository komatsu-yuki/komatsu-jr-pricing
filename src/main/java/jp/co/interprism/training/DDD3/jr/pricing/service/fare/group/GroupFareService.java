package jp.co.interprism.training.DDD3.jr.pricing.service.fare.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.DiscountedFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.Group;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.GroupDiscount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.OrdinaryFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.OrdinaryFareFactory;
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

        OrdinaryFareFactory ordinaryFareFactory = new OrdinaryFareFactory(basicFareFactory, superExpressSurchargeFactory);
        OrdinaryFare ordinaryFare = ordinaryFareFactory.create(boardingSection, boardingDate, seat, superExpressName);

        DiscountedFare groupFare = new DiscountedFare(ordinaryFare, groupDiscount);
        return groupFare.sumFareYen();
    }
}
