package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.Station;

public enum StartStationForm {
    東京;

    public Station parse() {
        return Station.valueOf(this.name());
    }
}
