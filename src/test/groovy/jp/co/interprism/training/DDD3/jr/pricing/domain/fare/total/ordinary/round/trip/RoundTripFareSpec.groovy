package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.OperatingKilometer
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class RoundTripFareSpec extends Specification {
    def "正常系: 運賃#basicfare円のときの往復運賃#result円"() {
        setup:
        def basicFareYen = new BasicFareYen(new FareYen(basicFare))
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(5920))
        def oneWayFare = new OneWayFare(basicFareYen, superExpressSurchargeYen)
        def operatingKilometer = new OperatingKilometer(644)
        def discount = new RoundTripDiscount(operatingKilometer)

        when:
        def roundTripFare = new RoundTripFare(oneWayFare, discount)

        then:
        assert roundTripFare.getBasicFareYen().fareYen.value == result

        where:
        basicFare || result
        10010     || 18000
    }

    def "正常系: 特急料金#superExpressSurcharge円のときの往復特急料金#result円"() {
        setup:
        def basicFareYen = new BasicFareYen(new FareYen(10010))
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(superExpressSurcharge))
        def oneWayFare = new OneWayFare(basicFareYen, superExpressSurchargeYen)
        def operatingKilometer = new OperatingKilometer(644)
        def discount = new RoundTripDiscount(operatingKilometer)

        when:
        def roundTripFare = new RoundTripFare(oneWayFare, discount)

        then:
        assert roundTripFare.getSuperExpressSurchargeYen().fareYen.value == result

        where:
        superExpressSurcharge || result
        5920                  || 11840
    }

    def "異常系: 往復割引の条件を満たしていない場合"() {
        setup:
        def basicFareYen = new BasicFareYen(new FareYen(8910))
        def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(5490))
        def oneWayFare = new OneWayFare(basicFareYen, superExpressSurchargeYen)
        def operatingKilometer = new OperatingKilometer(553)
        def discount = new RoundTripDiscount(operatingKilometer)

        def roundTripFare = new RoundTripFare(oneWayFare, discount)

        when:
        roundTripFare.getBasicFareYen()

        then:
        def e1 = thrown(IllegalArgumentException)
        assert e1.message == "割引が適用できません"

        when:
        roundTripFare.getSuperExpressSurchargeYen()

        then:
        def e2 = thrown(IllegalArgumentException)
        assert e2.message == "割引が適用できません"
    }
}
