package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Group {
    private final AdultsCount adultsCount;
    private final ChildrenCount childrenCount;

    public MembersCount sumMembersCount() {
        MembersCount adultMembersCount = adultsCount.getMembersCount();
        MembersCount childMembersCount = childrenCount.getMembersCount();
        return adultMembersCount.plus(childMembersCount);
    }
}
