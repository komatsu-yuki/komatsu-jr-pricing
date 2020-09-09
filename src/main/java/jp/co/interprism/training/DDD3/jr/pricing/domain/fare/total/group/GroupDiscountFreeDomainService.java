package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupDiscountFreeDomainService {
    private static final MembersCount NO_CHARGE_BOUND_FOR_ONE = new MembersCount(new FareCount(31));//31人以上で1人無料
    private static final MembersCount NO_CHARGE_CRITERION = new MembersCount(new FareCount(50));//50人増えるごとに1人ずつ無料

    private final GroupDiscount groupDiscount;

    private MembersCount calculateFreeMembersCount() {//TODO リファクタ考慮
        Group group = groupDiscount.getGroup();
        MembersCount groupMembersCount = group.sumMembersCount();
        if (groupMembersCount.compareTo(NO_CHARGE_BOUND_FOR_ONE) < 0) return MembersCount.ZERO;

        FareCount groupMembersFareCount = groupMembersCount.getFareCount();
        int groupMembersInt = groupMembersFareCount.getValue();

        FareCount freeCriterionFareCount = NO_CHARGE_CRITERION.getFareCount();
        int freeCriterionInt = freeCriterionFareCount.getValue();

        int freeCount = 1 + (groupMembersInt - 1) / freeCriterionInt;

        return new MembersCount(new FareCount(freeCount));
    }

    private AdultsCount calculateFreeAdultsCount() {
        Group group = groupDiscount.getGroup();
        AdultsCount adultsCount = group.getAdultsCount();
        MembersCount freeMembersCount = calculateFreeMembersCount();

        if (freeMembersCount.compareTo(adultsCount.getMembersCount()) >= 0) {
            return adultsCount;
        }
        return new AdultsCount(freeMembersCount);
    }

    private ChildrenCount calculateFreeChildrenCount() {
        MembersCount freeMembersCount = calculateFreeMembersCount();
        MembersCount freeAdultMembersCount = calculateFreeAdultsCount().getMembersCount();
        MembersCount freeChildrenMembersCount = freeMembersCount.minus(freeAdultMembersCount);
        return new ChildrenCount(freeChildrenMembersCount);
    }

    public AdultsCount calculatePaymentAdultsCount() {
        AdultsCount adultsCount = groupDiscount.getGroup().getAdultsCount();
        AdultsCount freeAdultsCount = calculateFreeAdultsCount();
        return adultsCount.minus(freeAdultsCount);
    }

    public ChildrenCount calculatePaymentChildrenCount() {
        ChildrenCount childrenCount = groupDiscount.getGroup().getChildrenCount();
        ChildrenCount freeChildrenCount = calculateFreeChildrenCount();
        return childrenCount.minus(freeChildrenCount);
    }
}
