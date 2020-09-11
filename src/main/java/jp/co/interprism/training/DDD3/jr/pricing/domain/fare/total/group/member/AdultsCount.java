package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdultsCount {
    private final MembersCount membersCount;

    public AdultsCount plus(AdultsCount that) {
        return new AdultsCount(this.membersCount.plus(that.membersCount));
    }

    public AdultsCount minus(AdultsCount that) {
        return new AdultsCount(this.membersCount.minus(that.membersCount));
    }

    public FareCount getFareCount() {
        return membersCount.getFareCount();
    }
}
