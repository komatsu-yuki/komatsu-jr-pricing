package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.OperatingKilometer
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class RoundTripDiscountSpec extends Specification {
    def "正常系: 営業キロが#eigyouKiloのとき利用可能かどうか"() {
        setup:
        def operatingKilometer = new OperatingKilometer(eigyouKilo)

        when:
        def roundTripDiscount = new RoundTripDiscount(operatingKilometer)

        then:
        assert roundTripDiscount.isAvailable() == result

        where:
        eigyouKilo || result
        600.9      || false
        601.0      || true
    }

    def "正常系: 片道運賃#basicFare円の往復割引後#result円"() {
        setup:
        def operatingKilometer = new OperatingKilometer(644)
        def basicFareYen = new BasicFareYen(new FareYen(basicFare))

        when:
        def roundTripDiscount = new RoundTripDiscount(operatingKilometer)

        then:
        assert roundTripDiscount.calculateBasicFareYen(basicFareYen).fareYen.value == result

        where:
        basicFare || result
        10010     || 9000
    }

    def "正常系: 特急料金は往復割引されない"() {
        setup:
        def operatingKilometer = new OperatingKilometer(644)
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(superExpressSurcharge))

        when:
        def roundTripDiscount = new RoundTripDiscount(operatingKilometer)

        then:
        assert roundTripDiscount.calculateSuperExpressSurchargeYen(superExpressSurchargeYen).fareYen.value == result

        where:
        superExpressSurcharge || result
        5920                  || 5920
    }
}
