package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

class GroupDiscountFreeSpec extends Specification {
    @Shared
    def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
    def discountInPeak = new GroupDiscountInPeak(boardingDate)

    def "正常系: 合計#total人の団体は#freeTotal人無料になる"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def discount = new GroupDiscount(group, discountInPeak)
        def freeDomainService = new GroupDiscountFreeDomainService(discount)

        then:
        assert adults + children == total
        assert adults - freeDomainService.calculatePaymentAdultsCount().membersCount.fareCount.value == freeAdults
        assert children - freeDomainService.calculatePaymentChildrenCount().membersCount.fareCount.value == freeChildren
        assert freeAdults + freeChildren == freeTotal

        where:
        adults | children | total || freeAdults | freeChildren | freeTotal
        21     | 9        | 30    || 0          | 0            | 0
        20     | 11       | 31    || 1          | 0            | 1
        37     | 13       | 50    || 1          | 0            | 1
        36     | 15       | 51    || 2          | 0            | 2
        75     | 25       | 100   || 2          | 0            | 2
        78     | 23       | 101   || 3          | 0            | 3
    }

    def "正常系: 無料人数が大人の人数より多い場合"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)

        when:
        def discount = new GroupDiscount(group, discountInPeak)
        def freeDomainService = new GroupDiscountFreeDomainService(discount)

        then:
        assert adults + children == total
        assert adults - freeDomainService.calculatePaymentAdultsCount().membersCount.fareCount.value == freeAdults
        assert children - freeDomainService.calculatePaymentChildrenCount().membersCount.fareCount.value == freeChildren
        assert freeAdults + freeChildren == freeTotal

        where:
        adults | children | total || freeAdults | freeChildren | freeTotal
        0      | 31       | 31    || 0          | 1            | 1
        1      | 50       | 51    || 1          | 1            | 2
        2      | 200      | 202   || 2          | 3            | 5
    }
}
