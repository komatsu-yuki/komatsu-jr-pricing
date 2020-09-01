package jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section;

import lombok.Getter;

@Getter
public enum Station {
    東京(new OperatingKilometer(0)),
    新大阪(new OperatingKilometer(552.6)),
    姫路(new OperatingKilometer(644.3));

    private final OperatingKilometer operatingKilometer;

    Station(OperatingKilometer operatingKilometer) {
        this.operatingKilometer = operatingKilometer;
    }
}
