package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.Season
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SuperExpressSurchargeReservedSeatSpec extends Specification {
    def "列車種が#trainType, シーズンが#seasonTypeのとき 指定席の特急料金は#result円"() {
        given:
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(5490))
        def additionalCharge = new AdditionalCharge(new FareYen(320))
        def superExpressName = SuperExpressName.valueOf(trainType)
        def season = Season.valueOf(seasonType)

        when:
        def superExpressSurcharge = new SuperExpressSurchargeReservedSeat(superExpressSurchargeYen,
                additionalCharge,
                superExpressName,
                season)

        then:
        assert superExpressSurcharge.calculateSuperExpressSurchargeYen().fareYen.value == result

        where:
        trainType | seasonType || result
        "ひかり"     | "REGULAR"  || 5490
        "ひかり"     | "PEAK"     || 5690
        "ひかり"     | "OFF_PEAK" || 5290
        "のぞみ"     | "REGULAR"  || 5810
        "のぞみ"     | "PEAK"     || 6010
        "のぞみ"     | "OFF_PEAK" || 5610
    }
}
