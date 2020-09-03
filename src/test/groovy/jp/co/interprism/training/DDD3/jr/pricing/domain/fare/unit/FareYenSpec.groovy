package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FareYenSpec extends Specification {

    def "コンストラクタ(#value)でvalueが#result"() {
        when:
        def fareYen = new FareYen(value)

        then:
        assert fareYen.value == result

        where:
        value | result
        1230  | 1230
        5678  | 5670
        5     | 0
        0     | 0
    }

    def "コンストラクタ(負の値)でIllegalArgumentExceptionが発生"() {
        when:
        new FareYen(-1000)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "料金が負の値です"
    }

    def "#value1円 + #value2円 = #result円"() {
        when:
        def fareYen1 = new FareYen(value1)
        def fareYen2 = new FareYen(value2)

        then:
        assert (fareYen1 + fareYen2).value == result

        where:
        value1 | value2 || result
        1000   | 2000   || 3000
        1009   | 2009   || 3000
        1000   | 0      || 1000
    }

    def "#value1円 - #value2円 = #result円"() {
        when:
        def fareYen1 = new FareYen(value1)
        def fareYen2 = new FareYen(value2)

        then:
        assert (fareYen1 - fareYen2).value == result

        where:
        value1 | value2 || result
        5000   | 2000   || 3000
        5000   | 2009   || 3000
        5000   | 0      || 5000
        3000   | 3009   || 0
    }

    def "2000円 - 5000円 でIllegalArgumentExceptionが発生"() {
        when:
        def fareYen1 = new FareYen(2000)
        def fareYen2 = new FareYen(5000)
        fareYen1 - fareYen2

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "料金が負の値です"
    }

    def "#value円 * #rate = #result円"() {
        when:
        def fareYen = new FareYen(value)
        def fareRate = new FareRate(rate)

        then:
        assert fareYen.times(fareRate).value == result

        where:
        value | rate  || result
        2000  | 0.5   || 1000
        1230  | 0.9   || 1100
        10000 | 0.999 || 9990
        2000  | 1.5   || 3000
        2000  | 0     || 0
        0     | 0.8   || 0
    }

    def "#value円 * #count回 = #result円"() {
        when:
        def fareYen = new FareYen(value)
        def fareCount = new FareCount(count)

        then:
        assert fareYen.times(fareCount).value == result

        where:
        value | count || result
        2000  | 3     || 6000
    }
}
