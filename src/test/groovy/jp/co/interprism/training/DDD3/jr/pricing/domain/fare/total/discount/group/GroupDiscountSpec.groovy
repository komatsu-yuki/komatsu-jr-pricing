package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.AdultsCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.ChildrenCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.MembersCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

@Unroll
class GroupDiscountSpec extends Specification {
    def "団体人数#totalのときに利用可能かどうか"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def groupDiscount = new GroupDiscount(group, boardingDate)

        then:
        assert total == adults + children
        assert groupDiscount.isAvailable() == result

        where:
        adults | children || total || result
        5      | 3        || 8     || true
        4      | 3        || 7     || false
    }

    def "大人と子どもの比に偏りな場合"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def groupDiscount = new GroupDiscount(group, boardingDate)

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
}
