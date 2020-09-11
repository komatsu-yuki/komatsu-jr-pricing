package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.Discount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupDiscount implements Discount {
    private static final FareRate DISCOUNT_RATE = new FareRate(0.85);//15%引き
    private static final MembersCount MEMBERS_LOWER_BOUND = new MembersCount(new FareCount(8));//最低8人

    private final Group group;
    private final GroupDiscountInPeak discountInPeak;

    @Override
    public boolean isAvailable() {
        return group.sumMembersCount().compareTo(MEMBERS_LOWER_BOUND) >= 0;
    }

    @Override
    public BasicFareYen calculateBasicFareYen(BasicFareYen basicFareYen) {
        return discountInPeak.inPeak()
                ? discountInPeak.calculateBasicFareYen(basicFareYen)
                : basicFareYen.times(DISCOUNT_RATE);
    }

    @Override
    public SuperExpressSurchargeYen calculateSuperExpressSurchargeYen(SuperExpressSurchargeYen superExpressSurchargeYen) {
        return superExpressSurchargeYen;
    }
}
