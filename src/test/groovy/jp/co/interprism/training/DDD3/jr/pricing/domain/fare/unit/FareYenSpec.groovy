package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit

import spock.lang.Specification
import spock.lang.Unroll

class FareYenSpec extends Specification {

    @Unroll
    def "コンストラクタ(#value)でvalueが#result"() {
        when:
        def fareYen = new FareYen(value)

        then:
        assert fareYen.value == result

        where:
        value | result
        1230  | 1230
        5678  | 5670
        1     | 0
    }
}
