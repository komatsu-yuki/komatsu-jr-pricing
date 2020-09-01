package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrdinaryFare implements TotalFare {
    private final BasicFareYen basicFareYen;
    private final SuperExpressSurchargeYen superExpressSurchargeYen;
}
