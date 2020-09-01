package jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.surchange.express;

import jp.co.interprism.training.DDD3.jr.pricing.datastore.section.BoardingSectionTableDirect;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurcharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.free.SuperExpressSurchargeFreeSeat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved.AdditionalCharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved.SuperExpressSurchargeReservedSeat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import org.springframework.stereotype.Component;

@Component
public class SuperExpressSurchargeFactoryDirect implements SuperExpressSurchargeFactory {
    @Override
    public SuperExpressSurcharge create(BoardingSection boardingSection, BoardingDate boardingDate, Seat seat, SuperExpressName superExpressName) {
        BoardingSectionTableDirect table = new BoardingSectionTableDirect();
        SuperExpressSurchargeYen superExpressSurchargeYen = table.toSuperExpressSurchargeYen(boardingSection);

        if (seat.isFree()) return new SuperExpressSurchargeFreeSeat(superExpressSurchargeYen);

        AdditionalCharge additionalCharge = table.toAdditionalCharge(boardingSection);
        return new SuperExpressSurchargeReservedSeat(superExpressSurchargeYen, additionalCharge, superExpressName, boardingDate.getSeason());
    }
}
