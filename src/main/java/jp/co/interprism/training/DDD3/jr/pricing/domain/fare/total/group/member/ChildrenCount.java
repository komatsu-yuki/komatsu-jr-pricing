package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChildrenCount {
    private final MembersCount membersCount;

    public ChildrenCount plus(ChildrenCount that) {
        return new ChildrenCount(this.membersCount.plus(that.membersCount));
    }

    public ChildrenCount minus(ChildrenCount that) {
        return new ChildrenCount(this.membersCount.minus(that.membersCount));
    }

    public FareCount getFareCount() {
        return membersCount.getFareCount();
    }
}
