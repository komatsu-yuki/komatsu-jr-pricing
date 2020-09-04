package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.ChildrenCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.MembersCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChildrenCountForm {
    private final String value;

    public ChildrenCount parse() {
        return new ChildrenCount(new MembersCount(new FareCount(Integer.parseInt(value))));
    }
}
