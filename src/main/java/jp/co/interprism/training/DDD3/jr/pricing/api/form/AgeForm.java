package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.child.Age;

public enum AgeForm {
    おとな,
    こども;

    public Age parse() {
        if (this.equals(おとな)) return Age.ADULT;
        if (this.equals(こども)) return Age.CHILD;
        throw new IllegalStateException();
    }
}
