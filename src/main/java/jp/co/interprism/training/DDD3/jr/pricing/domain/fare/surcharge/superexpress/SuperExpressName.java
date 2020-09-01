package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress;

public enum SuperExpressName {
    ひかり(AdditionalChargeFlag.NO_ADDITION),
    のぞみ(AdditionalChargeFlag.ADDITION);

    private final AdditionalChargeFlag additionalChargeFlag;

    SuperExpressName(AdditionalChargeFlag additionalChargeFlag) {
        this.additionalChargeFlag = additionalChargeFlag;
    }

    public boolean hasAdditionalCharge() {
        return this.additionalChargeFlag.equals(AdditionalChargeFlag.ADDITION);
    }


    enum AdditionalChargeFlag {
        ADDITION,
        NO_ADDITION;
    }
}
