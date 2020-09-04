package jp.co.interprism.training.DDD3.jr.pricing.api.group;

import jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.basic.BasicFareFactoryDirect;
import jp.co.interprism.training.DDD3.jr.pricing.datastore.fare.surchange.express.SuperExpressSurchargeFactoryDirect;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;
import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.Station;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeFactory;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen;
import jp.co.interprism.training.DDD3.jr.pricing.service.fare.group.GroupFareService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class GroupFareApi {
    public Map invoke(GroupFareRequest request) {
        Station startStation = request.getStartStationForm().parse();
        Station endStation = request.getEndStationForm().parse();
        BoardingSection boardingSection = new BoardingSection(startStation, endStation);

        BoardingDate boardingDate = request.getBoardingDateForm().parse();
        Seat seat = request.getSeatForm().parse();
        SuperExpressName superExpressName = request.getSuperExpressNameForm().parse();
        AdultsCount adultsCount = request.getAdultsCountForm().parse();
        ChildrenCount childrenCount = request.getChildrenCountForm().parse();

        BasicFareFactory basicFareFactory = new BasicFareFactoryDirect();
        SuperExpressSurchargeFactory superExpressSurchargeFactory = new SuperExpressSurchargeFactoryDirect();

        GroupFareService groupFareService = new GroupFareService(basicFareFactory, superExpressSurchargeFactory);
        FareYen fareYen = groupFareService.calculate(boardingSection, boardingDate, seat, superExpressName, adultsCount, childrenCount);

        Map<String, Object> res = new HashMap<>();
        res.put("団体料金", fareYen.getValue());
        return res;
    }
}
