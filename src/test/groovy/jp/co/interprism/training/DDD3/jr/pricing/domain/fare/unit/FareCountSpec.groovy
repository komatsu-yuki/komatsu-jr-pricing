package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FareCountSpec extends Specification {
    def "正常系: コンストラクタ"() {
        when:
        def fareCount = new FareCount(value)

        then:
        assert fareCount.value == result

        where:
        value || result
        4     || 4
    }

    def "正常系: 0個は許可される"() {
        when:
        new FareCount(0)

        then:
        noExceptionThrown()
    }

    def "異常系: 負の値でIllegalArgumentExceptionが発生する"() {
        when:
        new FareCount(-3)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message == "個数が負の値です"
    }

    def "正常系: #value1個 + #value2個 = #result個"() {
        when:
        def fareCount1 = new FareCount(value1)
        def fareCount2 = new FareCount(value2)

        then:
        assert (fareCount1 + fareCount2).value == result

        where:
        value1 | value2 || result
        5      | 8      || 13
    }

    def "正常系: #value1個 - #value2個 = #result個"() {
        when:
        def fareCount1 = new FareCount(value1)
        def fareCount2 = new FareCount(value2)

        then:
        assert (fareCount1 - fareCount2).value == result

        where:
        value1 | value2 || result
        14     | 6      || 8
    }

    def "異常系: #value1個 - #value2個でIllegalArgumentException"() {
        setup:
        def fareCount1 = new FareCount(value1)
        def fareCount2 = new FareCount(value2)

        when:
        fareCount1 - fareCount2
        then:
        thrown(IllegalArgumentException)

        where:
        value1 | value2
        5      | 8
    }

    def "正常系: #value1個 と #value2個 の比較"() {
        when:
        def fareCount1 = new FareCount(value1)
        def fareCount2 = new FareCount(value2)

        then:
        Math.signum(fareCount1 <=> fareCount2) == result

        where:
        value1 | value2 || result
        3      | 8      || -1
        7      | 6      || 1
        5      | 5      || 0
    }
}
