package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.child.ChildFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.Discount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member.MembersCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.OrdinaryFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class GroupDiscount implements Discount {
    private static final FareRate DISCOUNT_RATE_IN_GROUP_REGULAR = new FareRate(0.85);//15%引き
    private static final FareRate DISCOUNT_RATE_IN_GROUP_PEAK = new FareRate(0.9);//10%引き
    private static final MembersCount DISCOUNT_LOWER_BOUND = new MembersCount(new FareCount(8));//最低8人
    private static final MembersCount NO_CHARGE_BOUND_FOR_ONE = new MembersCount(new FareCount(31));//31人以上で1人無料
    private static final MembersCount NO_CHARGE_CRITERION = new MembersCount(new FareCount(50));//50人増えるごとに1人ずつ無料

    private final Group group;
    private final BoardingDate boardingDate;

    private boolean inGroupPeak() {
        LocalDate localDate = boardingDate.getLocalDate();
        int year = localDate.getYear();
        if (localDate.getMonthValue() == 12) {
            LocalDate peakStartDate = LocalDate.of(year, 12, 21);
            return localDate.isAfter(peakStartDate);
        }
        if (localDate.getMonthValue() == 1) {
            LocalDate peakEndDate = LocalDate.of(year, 1, 10);
            return localDate.isBefore(peakEndDate);
        }
        return false;
    }

    private BasicFareYen sumBasicFareYenForGroup(BasicFareYen basicFareYen, Group group) {// TODO: 2020/08/31 要リファクタ
        MembersCount adultMembersCount = group.getAdultsCount().getMembersCount();
        MembersCount childMembersCount = group.getChildrenCount().getMembersCount();

        ChildFare childFare = new ChildFare(new OrdinaryFare(basicFareYen, new SuperExpressSurchargeYen(FareYen.ZERO)));//TODO 要リファクタ
        BasicFareYen childYen = childFare.getBasicFareYen();

        BasicFareYen sumAdultsYen = basicFareYen.times(adultMembersCount.getFareCount());
        BasicFareYen sumChildrenYen = childYen.times(childMembersCount.getFareCount());

        return sumAdultsYen.plus(sumChildrenYen);
    }

    private SuperExpressSurchargeYen sumSuperExpressSurchargeYenForGroup(SuperExpressSurchargeYen superExpressSurchargeYen, Group group) {
        MembersCount adultMembersCount = group.getAdultsCount().getMembersCount();
        MembersCount childMembersCount = group.getChildrenCount().getMembersCount();

        ChildFare childFare = new ChildFare(new OrdinaryFare(new BasicFareYen(FareYen.ZERO), superExpressSurchargeYen));//TODO 要リファクタ
        SuperExpressSurchargeYen childYen = childFare.getSuperExpressSurchargeYen();

        SuperExpressSurchargeYen sumAdultsYen = childYen.times(adultMembersCount.getFareCount());
        SuperExpressSurchargeYen sumChildrenYen = childYen.times(childMembersCount.getFareCount());

        return sumAdultsYen.plus(sumChildrenYen);
    }

    private MembersCount getFreeMembersCount() {//TODO リファクタ考慮
        MembersCount groupMembersCount = group.sumMembersCount();
        if (groupMembersCount.compareTo(NO_CHARGE_BOUND_FOR_ONE) < 0) return MembersCount.ZERO;

        FareCount groupMembersFareCount = groupMembersCount.getFareCount();
        int groupMembersInt = groupMembersFareCount.getValue();

        FareCount freeCriterionFareCount = NO_CHARGE_CRITERION.getFareCount();
        int freeCriterionInt = freeCriterionFareCount.getValue();

        int freeCount = 1 + (groupMembersInt - 1) / freeCriterionInt;

        return new MembersCount(new FareCount(freeCount));
    }

    private Group getGroupForPayment() {
        MembersCount adultMembersCount = group.getAdultsCount().getMembersCount();
        MembersCount childMembersCount = group.getChildrenCount().getMembersCount();
        MembersCount freeMembersCount = getFreeMembersCount();

        if (freeMembersCount.compareTo(adultMembersCount) < 0) {
            MembersCount newAdultMembersCount = adultMembersCount.minus(freeMembersCount);
            return new Group(new AdultsCount(newAdultMembersCount), new ChildrenCount(childMembersCount));
        }

        MembersCount freeChildMembersCount = freeMembersCount.minus(adultMembersCount);
        MembersCount newAdultMembersCount = MembersCount.ZERO;
        MembersCount newChildMembersCount = childMembersCount.minus(freeChildMembersCount);
        return new Group(new AdultsCount(newAdultMembersCount), new ChildrenCount(newChildMembersCount));
    }

    @Override
    public boolean isAvailable() {
        return group.sumMembersCount().compareTo(DISCOUNT_LOWER_BOUND) >= 0;
    }

    @Override
    public BasicFareYen discountBasicFareYen(BasicFareYen basicFareYen) {
        Group groupForPayment = getGroupForPayment();
        BasicFareYen discountedYen = discountBasicFareYenForOneTime(basicFareYen);
        return sumBasicFareYenForGroup(discountedYen, groupForPayment);
    }

    @Override
    public SuperExpressSurchargeYen discountSuperExpressSurchargeYen(SuperExpressSurchargeYen superExpressSurchargeYen) {
        Group groupForPayment = getGroupForPayment();
        SuperExpressSurchargeYen discountedYen = discountSuperExpressSurchargeYenForOneTime(superExpressSurchargeYen);
        return sumSuperExpressSurchargeYenForGroup(discountedYen, groupForPayment);
    }

    @Override
    public BasicFareYen discountBasicFareYenForOneTime(BasicFareYen basicFareYen) {
        if (inGroupPeak()) return basicFareYen.times(DISCOUNT_RATE_IN_GROUP_PEAK);
        return basicFareYen.times(DISCOUNT_RATE_IN_GROUP_REGULAR);
    }

    @Override
    public SuperExpressSurchargeYen discountSuperExpressSurchargeYenForOneTime(SuperExpressSurchargeYen superExpressSurchargeYen) {
        return superExpressSurchargeYen;
    }
}
