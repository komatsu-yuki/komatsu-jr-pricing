package jp.co.interprism.training.DDD3.jr.pricing.api.round.trip;

import jp.co.interprism.training.DDD3.jr.pricing.api.form.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoundTripFareRequest {
    private StartStationForm startStationForm;
    private EndStationForm endStationForm;
    private BoardingDateForm boardingDateForm;
    private SeatForm seatForm;
    private SuperExpressNameForm superExpressNameForm;
    private AgeForm ageForm;

    public RoundTripFareRequest() {
        this.startStationForm = StartStationForm.東京;
        this.seatForm = SeatForm.指定席;
        this.superExpressNameForm = SuperExpressNameForm.ひかり;
        this.ageForm = AgeForm.おとな;
    }
}
