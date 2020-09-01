package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;

public interface Discount {
    boolean isAvailable();

    BasicFareYen discountBasicFareYen(BasicFareYen basicFareYen);

    SuperExpressSurchargeYen discountSuperExpressSurchargeYen(SuperExpressSurchargeYen superExpressSurchargeYen);

    BasicFareYen discountBasicFareYenForOneTime(BasicFareYen basicFareYen);

    SuperExpressSurchargeYen discountSuperExpressSurchargeYenForOneTime(SuperExpressSurchargeYen superExpressSurchargeYen);
}
