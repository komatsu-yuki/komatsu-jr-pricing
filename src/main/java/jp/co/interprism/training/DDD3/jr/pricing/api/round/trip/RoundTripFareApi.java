package jp.co.interprism.training.DDD3.jr.pricing.api.round.trip;

import jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.basic.BasicFareFactoryDirect;
import jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.surchange.express.SuperExpressSurchargeFactoryDirect;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.Station;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.child.Age;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import jp.co.interprism.training.DDD3.jr.pricing.service.fare.ordinary.RoundTripFareService;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RoundTripFareApi {
    public Map invoke(RoundTripFareRequest request) {
        Station startStation = request.getStartStationForm().parse();
        Station endStation = request.getEndStationForm().parse();
        BoardingSection boardingSection = new BoardingSection(startStation, endStation);

        BoardingDate boardingDate = request.getBoardingDateForm().parse();
        Seat seat = request.getSeatForm().parse();
        SuperExpressName superExpressName = request.getSuperExpressNameForm().parse();
        Age age = request.getAgeForm().parse();

        BasicFareFactory basicFareFactory = new BasicFareFactoryDirect();
        SuperExpressSurchargeFactory superExpressSurchargeFactory = new SuperExpressSurchargeFactoryDirect();

        RoundTripFareService roundTripFareService = new RoundTripFareService(basicFareFactory, superExpressSurchargeFactory);
        FareYen fareYen = roundTripFareService.calculate(boardingSection, boardingDate, seat, superExpressName, age);

        Map<String, Object> res = new HashMap<>();
        res.put("往復料金", fareYen.getValue());
        return res;
    }
}
