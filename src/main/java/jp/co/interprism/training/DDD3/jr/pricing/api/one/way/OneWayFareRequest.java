package jp.co.interprism.training.DDD3.jr.pricing.api.one.way;

import jp.co.interprism.training.DDD3.jr.pricing.api.form.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OneWayFareRequest {
    private StartStationForm startStationForm;
    private EndStationForm endStationForm;
    private BoardingDateForm boardingDateForm;
    private SeatForm seatForm;
    private SuperExpressNameForm superExpressNameForm;
    private AgeForm ageForm;

    public OneWayFareRequest() {
        this.startStationForm = StartStationForm.東京;
        this.seatForm = SeatForm.指定席;
        this.superExpressNameForm = SuperExpressNameForm.ひかり;
        this.ageForm = AgeForm.おとな;
    }
}
