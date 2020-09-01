package jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.basic;

import jp.co.interprism.training.DDD3.jr.pricing.datastore.section.BoardingSectionTableDirect;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFare;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import org.springframework.stereotype.Component;

@Component
public class BasicFareFactoryDirect implements BasicFareFactory {
    @Override
    public BasicFare create(BoardingSection boardingSection) {
        BoardingSectionTableDirect table = new BoardingSectionTableDirect();
        BasicFareYen basicFareYen = table.toBasicFareYen(boardingSection);
        return new BasicFare(basicFareYen);
    }
}
