package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.discount.group.member.MembersCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

@Unroll
class GroupDiscountSpec extends Specification {
    def "団体人数が#団体人数のときに利用可能かどうか"() {
        setup:
        def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 3))
        def group = Stub(Group)

        group.sumMembersCount() >> new MembersCount(new FareCount(団体人数))

        when:
        def groupDiscount = new GroupDiscount(group, boardingDate)

        then:
        assert groupDiscount.isAvailable() == result

        where:
        団体人数 || result
        7    || false
        8    || true
    }
}
