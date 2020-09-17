package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.seat.free

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SuperExpressSurchargeFreeSeatSpec extends Specification {
    def "正常系: 自由席の料金"() {
        when:
        def superExpressSurcharge = new SuperExpressSurchargeFreeSeat(new SuperExpressSurchargeYen(new FareYen(yen)))

        then:
        assert superExpressSurcharge.calculateSuperExpressSurchargeYen().fareYen.value == result

        where:
        yen   || result
        5490  || 4960
    }
}
