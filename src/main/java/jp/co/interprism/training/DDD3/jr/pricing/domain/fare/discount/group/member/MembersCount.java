package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.discount.group.member;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembersCount implements Comparable<MembersCount> {
    public static final MembersCount ZERO = new MembersCount(FareCount.ZERO);

    private final FareCount fareCount;

    public MembersCount plus(MembersCount that) {
        return new MembersCount(fareCount.plus(that.fareCount));
    }

    public MembersCount minus(MembersCount that) {
        return new MembersCount(fareCount.minus(that.fareCount));
    }

    @Override
    public int compareTo(MembersCount that) {
        return this.fareCount.compareTo(that.fareCount);
    }
}
