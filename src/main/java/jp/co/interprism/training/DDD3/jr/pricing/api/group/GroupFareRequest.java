package jp.co.interprism.training.DDD3.jr.pricing.api.group;

import jp.co.interprism.training.DDD3.jr.pricing.api.form.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupFareRequest {
    private StartStationForm startStationForm;
    private EndStationForm endStationForm;
    private BoardingDateForm boardingDateForm;
    private SeatForm seatForm;
    private SuperExpressNameForm superExpressNameForm;
    private AdultsCountForm adultsCountForm;
    private ChildrenCountForm childrenCountForm;

    public GroupFareRequest() {
        this.startStationForm = StartStationForm.東京;
        this.seatForm = SeatForm.指定席;
        this.superExpressNameForm = SuperExpressNameForm.ひかり;
    }
}
