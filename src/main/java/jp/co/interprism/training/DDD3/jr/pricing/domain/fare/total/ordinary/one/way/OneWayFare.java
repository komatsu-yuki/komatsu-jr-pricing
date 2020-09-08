package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.TotalFare;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OneWayFare implements TotalFare {
    private final BasicFareYen basicFareYen;
    private final SuperExpressSurchargeYen superExpressSurchargeYen;
}
