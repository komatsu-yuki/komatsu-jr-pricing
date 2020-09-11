package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

@Unroll
class GroupDiscountSpec extends Specification {
    @Shared
    def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
    def discountInPeak = new GroupDiscountInPeak(boardingDate)

    def "正常系: 団体人数#totalのときに利用可能かどうか"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def groupDiscount = new GroupDiscount(group, discountInPeak)

        then:
        assert total == adults + children
        assert groupDiscount.isAvailable() == result

        where:
        adults | children || total || result
        6      | 2        || 8     || true
        4      | 3        || 7     || false
    }

    def "正常系: 大人と子どもの比に偏りがある場合"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def groupDiscount = new GroupDiscount(group, discountInPeak)

        then:
        assert total == adults + children
        assert groupDiscount.isAvailable() == result

        where:
        adults | children || total || result
        8      | 0        || 8     || true
        0      | 8        || 8     || true
        7      | 0        || 7     || false
        0      | 7        || 7     || false
    }

    def "正常系: #month月#date日のときの割引後運賃が#result円"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, month, date))
        def discountInPeak = new GroupDiscountInPeak(boardingDate)
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(8)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(0)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def discount = new GroupDiscount(group, discountInPeak)
        def basicFareYen = new BasicFareYen(new FareYen(10010))

        then:
        assert discount.calculateBasicFareYen(basicFareYen).fareYen.value == result

        where:
        month | date || result
        12    | 20   || 8500
        12    | 21   || 9000
        1     | 10   || 9000
        1     | 11   || 8500
    }
}
