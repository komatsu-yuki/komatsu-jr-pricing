package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.section.OperatingKilometer
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.round.trip.RoundTripDiscount
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class RoundTripDiscountSpec extends Specification {
    def "営業キロが#営業キロのとき利用可能かどうか"() {
        setup:
        def operatingKilometer = new OperatingKilometer(営業キロ)

        when:
        def roundTripDiscount = new RoundTripDiscount(operatingKilometer)

        then:
        assert roundTripDiscount.isAvailable() == result

        where:
        営業キロ  || result
        580   || false
        630   || true
        600   || false
        600.9 || false
        601   || true
        601.1 || true
    }
}
