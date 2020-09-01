package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;

public interface TotalFare {
    BasicFareYen getBasicFareYen();

    SuperExpressSurchargeYen getSuperExpressSurchargeYen();

    default FareYen sumFareYen() {
        BasicFareYen basicFareYen = getBasicFareYen();
        SuperExpressSurchargeYen superExpressSurchargeYen = getSuperExpressSurchargeYen();
        System.out.println("運賃=" + basicFareYen.getFareYen().getValue() + ", 特急料金=" + superExpressSurchargeYen.getFareYen().getValue());
        return basicFareYen.getFareYen().plus(superExpressSurchargeYen.getFareYen());
    }
}
