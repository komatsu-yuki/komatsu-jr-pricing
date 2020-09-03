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
        return basicFareYen.getFareYen().plus(superExpressSurchargeYen.getFareYen());
    }
}
