package jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class BoardingSection {
    private final Station start;
    private final Station end;

    //向きを固定する
    public BoardingSection(Station start, Station end) {
        if (start.compareTo(end) < 0) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }

    public OperatingKilometer calculateOperatingKilometer() {
        return end.getOperatingKilometer().minus(start.getOperatingKilometer());
    }
}
