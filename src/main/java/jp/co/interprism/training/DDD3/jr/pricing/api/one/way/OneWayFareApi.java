package jp.co.interprism.training.DDD3.jr.pricing.api.one.way;

import jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.basic.BasicFareFactoryDirect;
import jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.surchange.express.SuperExpressSurchargeFactoryDirect;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.Station;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.child.Age;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import jp.co.interprism.training.DDD3.jr.pricing.service.fare.ordinary.OneWayFareService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OneWayFareApi {

    @RequestMapping(value = "/jr-pricing")
    public Map invoke(OneWayFareRequest request) {
        Station startStation = request.getStartStationForm().parse();
        Station endStation = request.getEndStationForm().parse();
        BoardingSection boardingSection = new BoardingSection(startStation, endStation);

        BoardingDate boardingDate = request.getBoardingDateForm().parse();
        Seat seat = request.getSeatForm().parse();
        SuperExpressName superExpressName = request.getSuperExpressNameForm().parse();
        Age age = request.getAgeForm().parse();

        BasicFareFactory basicFareFactory = new BasicFareFactoryDirect();
        SuperExpressSurchargeFactory superExpressSurchargeFactory = new SuperExpressSurchargeFactoryDirect();

        OneWayFareService oneWayFareService = new OneWayFareService(basicFareFactory, superExpressSurchargeFactory);
        FareYen fareYen = oneWayFareService.calculate(boardingSection, boardingDate, seat, superExpressName, age);

        Map<String, Object> res = new HashMap<>();
        res.put("片道料金", fareYen.getValue());
        return res;
    }
}
