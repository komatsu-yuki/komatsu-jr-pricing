package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.ChildFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupFare implements TotalFare {
    private final TotalFare totalFare;
    private final GroupDiscount discount;

    @Override
    public BasicFareYen getBasicFareYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引が適用できません");

        BasicFareYen OnePersonYen = totalFare.getBasicFareYen();
        Group groupForPayment = discount.getGroupForPayment();

        MembersCount adultMembersCount = groupForPayment.getAdultsCount().getMembersCount();
        MembersCount childMembersCount = groupForPayment.getChildrenCount().getMembersCount();

        ChildFare childFare = new ChildFare(new OneWayFare(OnePersonYen, new SuperExpressSurchargeYen(FareYen.ZERO)));//TODO 要リファクタ
        BasicFareYen childYen = childFare.getBasicFareYen();

        BasicFareYen sumAdultsYen = OnePersonYen.times(adultMembersCount.getFareCount());
        BasicFareYen sumChildrenYen = childYen.times(childMembersCount.getFareCount());

        return sumAdultsYen.plus(sumChildrenYen);
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引が適用できません");
        SuperExpressSurchargeYen onePersonYen = totalFare.getSuperExpressSurchargeYen();
        Group groupForPayment = discount.getGroupForPayment();

        MembersCount adultMembersCount = groupForPayment.getAdultsCount().getMembersCount();
        MembersCount childMembersCount = groupForPayment.getChildrenCount().getMembersCount();

        ChildFare childFare = new ChildFare(new OneWayFare(new BasicFareYen(FareYen.ZERO), onePersonYen));//TODO 要リファクタ
        SuperExpressSurchargeYen childYen = childFare.getSuperExpressSurchargeYen();

        SuperExpressSurchargeYen sumAdultsYen = childYen.times(adultMembersCount.getFareCount());
        SuperExpressSurchargeYen sumChildrenYen = childYen.times(childMembersCount.getFareCount());

        return sumAdultsYen.plus(sumChildrenYen);
    }
}
