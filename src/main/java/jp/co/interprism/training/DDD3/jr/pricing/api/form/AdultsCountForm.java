package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdultsCountForm {
    private final String value;

    public AdultsCount parse() {
        return new AdultsCount(new MembersCount(new FareCount(Integer.parseInt(value))));
    }
}
