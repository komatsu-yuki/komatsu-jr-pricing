package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;

public interface Discount {
    boolean isAvailable();

    BasicFareYen calculateBasicFareYen(BasicFareYen basicFareYen);

    SuperExpressSurchargeYen calculateSuperExpressSurchargeYen(SuperExpressSurchargeYen superExpressSurchargeYen);

    BasicFareYen calculateBasicFareYenForOneTime(BasicFareYen basicFareYen);

    SuperExpressSurchargeYen calculateSuperExpressSurchargeYenForOneTime(SuperExpressSurchargeYen superExpressSurchargeYen);
}
