package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

@Unroll
class GroupDiscountSpec extends Specification {
    def "正常系: 団体人数#totalのときに利用可能かどうか"() {
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
        6      | 2        || 8     || true
        4      | 3        || 7     || false
    }

    def "正常系: 大人と子どもの比に偏りがある場合"() {
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

    def "正常系: 合計#total人の団体は#freeTotal人無料になる"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def discount = new GroupDiscount(group, boardingDate)
        def groupForPayment = discount.getGroupForPayment()

        then:
        assert adults + children == total
        assert adults - groupForPayment.adultsCount.membersCount.fareCount.value == freeAdults
        assert children - groupForPayment.childrenCount.membersCount.fareCount.value == freeChildren
        assert freeAdults + freeChildren == freeTotal

        where:
        adults | children | total || freeAdults | freeChildren | freeTotal
        21     | 9        | 30    || 0          | 0            | 0
        20     | 11       | 31    || 1          | 0            | 1
        37     | 13       | 50    || 1          | 0            | 1
        36     | 15       | 51    || 2          | 0            | 2
        75     | 25       | 100   || 2          | 0            | 2
        78     | 23       | 101   || 3          | 0            | 3
        120    | 30       | 150   || 3          | 0            | 3
        115    | 36       | 151   || 4          | 0            | 4
    }

    def "正常系: 無料人数が大人の人数より多い場合"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def discount = new GroupDiscount(group, boardingDate)
        def groupForPayment = discount.getGroupForPayment()

        then:
        assert adults + children == total
        assert adults - groupForPayment.adultsCount.membersCount.fareCount.value == freeAdults
        assert children - groupForPayment.childrenCount.membersCount.fareCount.value == freeChildren
        assert freeAdults + freeChildren == freeTotal

        where:
        adults | children | total || freeAdults | freeChildren | freeTotal
        0      | 31       | 31    || 0          | 1            | 1
        1      | 50       | 51    || 1          | 1            | 2
        2      | 200      | 202   || 2          | 3            | 5
    }

    def "正常系: 割引後の一人分の運賃"()
}
