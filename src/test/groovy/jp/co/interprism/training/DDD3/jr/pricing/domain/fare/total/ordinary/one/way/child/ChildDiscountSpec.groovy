package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.child

import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ChildDiscountSpec extends Specification {
    @Shared
    def discount = new ChildDiscount()

    def "正常系: 大人運賃#basicFare円のときの子ども運賃#result円"() {
        when:
        def basicFareYen = new BasicFareYen(new FareYen(basicFare))

        then:
        assert discount.calculateBasicFareYen(basicFareYen).fareYen.value == result

        where:
        basicFare || result
        8910      || 4450
        10010     || 5000
    }

    def "正常系: 大人特急料金#superExpressSurcharge円のときの子ども特急料金#result円"() {
        when:
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(superExpressSurcharge))

        then:
        assert discount.calculateSuperExpressSurchargeYen(superExpressSurchargeYen).fareYen.value == result

        where:
        superExpressSurcharge || result
        5490                  || 2740
        5920                  || 2960
    }
}
