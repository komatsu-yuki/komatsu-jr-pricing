package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FareYenSpec extends Specification {

    def "正常系: #value円の端数切り捨てで#result円"() {
        when:
        def fareYen = new FareYen(value)

        then:
        assert fareYen.value == result

        where:
        value | result
        1560  | 1560
        1234  | 1230
        99999 | 99990
    }

    def "正常系: 0円は許可される"() {
        when:
        new FareYen(0)

        then:
        noExceptionThrown()
    }

    def "異常系: 負の値でIllegalArgumentExceptionが発生"() {
        when:
        new FareYen(-1000)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "料金が負の値です"
    }

    def "異常系: 最大値を超えるとIllegalArgumentExceptionが発生"() {
        setup:
        int max = Integer.MAX_VALUE

        when:
        new FareYen(max)

        then:
        noExceptionThrown()

        when:
        new FareYen(max + 1)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "料金が負の値です"
    }

    def "正常系: #value1円 + #value2円 = #result円"() {
        when:
        def fareYen1 = new FareYen(value1)
        def fareYen2 = new FareYen(value2)

        then:
        assert (fareYen1 + fareYen2).value == result

        where:
        value1 | value2 || result
        1000   | 2000   || 3000
        7540   | 8390   || 15930
    }

    def "正常系: 端数の繰り上がりがある場合 #value1円 + #value2円 = #result円"() {
        when:
        def fareYen1 = new FareYen(value1)
        def fareYen2 = new FareYen(value2)

        then:
        assert (fareYen1 + fareYen2).value == result

        where:
        value1 | value2 || result
        1009   | 2009   || 3000
    }

    def "正常系: #value1円 - #value2円 = #result円"() {
        when:
        def fareYen1 = new FareYen(value1)
        def fareYen2 = new FareYen(value2)

        then:
        assert (fareYen1 - fareYen2).value == result

        where:
        value1 | value2 || result
        5000   | 2000   || 3000
    }

    def "異常系: #value1円 - #value2円 でIllegalArgumentExceptionが発生"() {
        setup:
        def fareYen1 = new FareYen(value1)
        def fareYen2 = new FareYen(value2)

        when:
        fareYen1 - fareYen2

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "料金が負の値です"

        where:
        value1 | value2
        2000   | 5000
    }

    def "正常系: #value円 * #rate = #result円"() {
        when:
        def fareYen = new FareYen(value)
        def fareRate = new FareRate(rate)

        then:
        assert fareYen.times(fareRate).value == result

        where:
        value | rate || result
        2000  | 0.5  || 1000
        1230  | 0.9  || 1100
    }

    def "正常系: #value円 * #count回 = #result円"() {
        when:
        def fareYen = new FareYen(value)
        def fareCount = new FareCount(count)

        then:
        assert fareYen.times(fareCount).value == result

        where:
        value | count || result
        2110  | 3     || 6330
    }
}
