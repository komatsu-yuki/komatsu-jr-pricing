package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.Station;

public enum EndStationForm {
    新大阪,
    姫路;

    public Station parse() {
        return Station.valueOf(this.name());
    }
}
