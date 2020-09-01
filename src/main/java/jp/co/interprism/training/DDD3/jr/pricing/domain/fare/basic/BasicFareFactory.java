package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.BoardingSection;

public interface BasicFareFactory {
    BasicFare create(BoardingSection boardingSection);
}
