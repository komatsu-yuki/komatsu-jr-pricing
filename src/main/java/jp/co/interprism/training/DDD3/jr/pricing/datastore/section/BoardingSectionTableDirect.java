package jp.co.interprism.training.DDD3.jr.pricing.datastore.section;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved.AdditionalCharge;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.Station;

public class BoardingSectionTableDirect {
    public static final BoardingSection 東京_新大阪 = new BoardingSection(Station.東京, Station.新大阪);
    public static final BoardingSection 東京_姫路 = new BoardingSection(Station.東京, Station.姫路);

    public BasicFareYen toBasicFareYen(BoardingSection section) {
        if (section.equals(東京_新大阪)) return new BasicFareYen(new FareYen(8910));
        if (section.equals(東京_姫路)) return new BasicFareYen(new FareYen(10010));
        throw new IllegalArgumentException("未実装");
    }

    public SuperExpressSurchargeYen toSuperExpressSurchargeYen(BoardingSection section) {
        if (section.equals(東京_新大阪)) return new SuperExpressSurchargeYen(new FareYen(5490));
        if (section.equals(東京_姫路)) return new SuperExpressSurchargeYen(new FareYen(5920));
        throw new IllegalArgumentException("未実装");
    }

    public AdditionalCharge toAdditionalCharge(BoardingSection section) {
        if (section.equals(東京_新大阪)) return new AdditionalCharge(new FareYen(320));
        if (section.equals(東京_姫路)) return new AdditionalCharge(new FareYen(530));
        throw new IllegalArgumentException("未実装");
    }
}
