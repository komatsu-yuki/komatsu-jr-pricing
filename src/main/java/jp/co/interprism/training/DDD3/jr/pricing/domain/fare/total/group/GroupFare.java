package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child.ChildDiscount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupFare implements TotalFare {
    private final TotalFare totalFare;
    private final GroupDiscount discount;

    @Override
    public BasicFareYen getBasicFareYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引が適用できません");
        //割引前の一人分運賃
        BasicFareYen adultYen = totalFare.getBasicFareYen();
        ChildDiscount childDiscount = new ChildDiscount();
        BasicFareYen childYen = childDiscount.calculateBasicFareYen(adultYen);

        //割引後の一人分運賃
        BasicFareYen discountedAdultYen = discount.calculateBasicFareYen(adultYen);
        BasicFareYen discountedChildYen = discount.calculateBasicFareYen(childYen);

        //無料分を除いた人数
        GroupDiscountFreeDomainService freeDomainService = new GroupDiscountFreeDomainService(discount);
        AdultsCount paymentAdultsCount = freeDomainService.calculatePaymentAdultsCount();
        ChildrenCount paymentChildrenCount = freeDomainService.calculatePaymentChildrenCount();

        //合計運賃
        BasicFareYen sumAdultsYen = discountedAdultYen.times(paymentAdultsCount.getMembersCount().getFareCount());
        BasicFareYen sumChildrenYen = discountedChildYen.times(paymentChildrenCount.getMembersCount().getFareCount());

        return sumAdultsYen.plus(sumChildrenYen);
    }

    @Override
    public SuperExpressSurchargeYen getSuperExpressSurchargeYen() {
        if (!discount.isAvailable()) throw new IllegalArgumentException("割引が適用できません");
        //割引前の一人分特急料金
        SuperExpressSurchargeYen adultYen = totalFare.getSuperExpressSurchargeYen();
        ChildDiscount childDiscount = new ChildDiscount();
        SuperExpressSurchargeYen childYen = childDiscount.calculateSuperExpressSurchargeYen(adultYen);

        //割引後の一人分特急料金
        SuperExpressSurchargeYen discountedAdultYen = discount.calculateSuperExpressSurchargeYen(adultYen);
        SuperExpressSurchargeYen discountedChildYen = discount.calculateSuperExpressSurchargeYen(childYen);

        //無料分を除いた人数
        GroupDiscountFreeDomainService freeDomainService = new GroupDiscountFreeDomainService(discount);
        AdultsCount paymentAdultsCount = freeDomainService.calculatePaymentAdultsCount();
        ChildrenCount paymentChildrenCount = freeDomainService.calculatePaymentChildrenCount();

        //合計特急料金
        SuperExpressSurchargeYen sumAdultsYen = discountedAdultYen.times(paymentAdultsCount.getMembersCount().getFareCount());
        SuperExpressSurchargeYen sumChildrenYen = discountedChildYen.times(paymentChildrenCount.getMembersCount().getFareCount());

        return sumAdultsYen.plus(sumChildrenYen);
    }
}
