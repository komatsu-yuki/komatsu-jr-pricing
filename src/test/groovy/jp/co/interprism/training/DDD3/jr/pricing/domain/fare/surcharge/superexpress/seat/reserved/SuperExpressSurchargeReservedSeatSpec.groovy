package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.reserved

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.Season
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressName
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SuperExpressSurchargeReservedSeatSpec extends Specification {
    def "列車種が#列車種, シーズンが#シーズンのとき 指定席の特急料金は#result円"() {
        given:
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(5490))
        def additionalCharge = new AdditionalCharge(new FareYen(320))
        def superExpressName = SuperExpressName.valueOf(列車種)
        def season = Season.valueOf(シーズン)

        when:
        def superExpressSurcharge = new SuperExpressSurchargeReservedSeat(superExpressSurchargeYen, additionalCharge, superExpressName, season)

        then:
        assert superExpressSurcharge.calculateSuperExpressSurchargeYen().fareYen.value == result

        where:
        列車種   | シーズン       || result
        "ひかり" | "PEAK"     || 5690
        "ひかり" | "OFF_PEAK" || 5290
        "のぞみ" | "REGULAR"  || 5810
    }
}
