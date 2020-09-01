package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.Seat;

public enum SeatForm {
    指定席,
    自由席;

    public Seat parse() {
        if (this.equals(指定席)) return Seat.RESERVED;
        if (this.equals(自由席)) return Seat.FREE;
        throw new IllegalStateException();
    }
}
