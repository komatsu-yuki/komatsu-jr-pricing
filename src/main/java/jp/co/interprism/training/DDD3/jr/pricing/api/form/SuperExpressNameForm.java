package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName;

public enum SuperExpressNameForm {
    ひかり,
    のぞみ;

    public SuperExpressName parse() {
        return SuperExpressName.valueOf(this.name());
    }
}
