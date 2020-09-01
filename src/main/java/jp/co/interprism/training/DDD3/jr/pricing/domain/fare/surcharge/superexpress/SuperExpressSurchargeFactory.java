package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;

public interface SuperExpressSurchargeFactory {
    SuperExpressSurcharge create(BoardingSection boardingSection,
                                 BoardingDate boardingDate,
                                 Seat seat,
                                 SuperExpressName superExpressName);
}
