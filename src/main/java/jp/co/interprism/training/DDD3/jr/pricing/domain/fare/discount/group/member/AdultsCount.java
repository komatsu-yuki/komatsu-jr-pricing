package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdultsCount {
    private final MembersCount membersCount;
}
