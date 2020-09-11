package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.surcharge.superexpress.SuperExpressSurchargeYen
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.AdultsCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.ChildrenCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group.member.MembersCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.ordinary.one.way.OneWayFare
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareCount
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareYen
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

@Unroll
class GroupFareSpec extends Specification {
    @Shared
    def basicFareYen = new BasicFareYen(new FareYen(8910))
    def superExpressSurchargeYen = new SuperExpressSurchargeYen(new FareYen(5490))
    def oneWayFare = new OneWayFare(basicFareYen, superExpressSurchargeYen)
    def boardingDate = new BoardingDate(LocalDate.of(2020, 9, 11))

    def "正常系: 大人#adults人, 子ども#children人のときの団体運賃#result円"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)
        def discount = new GroupDiscount(group, boardingDate)

        when:
        def groupFare = new GroupFare(oneWayFare, discount)

        then:
        assert adults + children == total
        assert groupFare.getBasicFareYen().fareYen.value == result

        where:
        adults | children | total || result
        6      | 4        | 10    || 60540
        10     | 22       | 32    || 151290
    }

    def "正常系: 大人#adults人, 子ども#children人のときの団体特急料金#result円"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(adults)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(children)))
        def group = new Group(adultsCount, childrenCount)
        def discount = new GroupDiscount(group, boardingDate)

        when:
        def groupFare = new GroupFare(oneWayFare, discount)

        then:
        assert adults + children == total
        assert groupFare.getSuperExpressSurchargeYen().fareYen.value == result

        where:
        adults | children | total || result
        6      | 4        | 10    || 43900
        10     | 22       | 32    || 109690
    }

    def "異常系: 団体割引の条件を満たしていない場合"() {
        setup:
        def adultsCount = new AdultsCount(new MembersCount(new FareCount(3)))
        def childrenCount = new ChildrenCount(new MembersCount(new FareCount(4)))
        def group = new Group(adultsCount, childrenCount)
        def discount = new GroupDiscount(group, boardingDate)
        def groupFare = new GroupFare(oneWayFare, discount)

        when:
        groupFare.getBasicFareYen()

        then:
        def e1 = thrown(IllegalArgumentException)
        assert e1.message == "割引が適用できません"

        when:
        groupFare.getSuperExpressSurchargeYen()

        then:
        def e2 = thrown(IllegalArgumentException)
        assert e2.message == "割引が適用できません"
    }
}
