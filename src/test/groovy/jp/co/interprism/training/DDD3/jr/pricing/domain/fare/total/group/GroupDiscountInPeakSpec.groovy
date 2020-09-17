package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

@Unroll
class GroupDiscountInPeakSpec extends Specification {
    def "正常系: #month月#date日のとき割引率が低くなるか"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, month, date))

        when:
        def discountInPeak = new GroupDiscountInPeak(boardingDate)

        then:
        assert discountInPeak.inPeak() == result

        where:
        month | date || result
        12    | 20   || false
        12    | 21   || true
        1     | 10   || true
        1     | 11   || false
    }

    def "正常系: 運賃#basicFare円のときの割引後運賃が#result円"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, 1, 1))
        def basicFareYen = new BasicFareYen(new FareYen(basicfare))

        when:
        def discountInPeak = new GroupDiscountInPeak(boardingDate)

        then:
        assert discountInPeak.calculateBasicFareYen(basicFareYen).fareYen.value == result

        where:
        basicfare || result
        8910      || 8010
        10010     || 9000
    }
}
