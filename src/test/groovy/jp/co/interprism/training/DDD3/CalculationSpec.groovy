package jp.co.interprism.training.DDD3

import jp.co.interprism.training.DDD3.jr.pricing.api.form.*
import jp.co.interprism.training.DDD3.jr.pricing.api.one.way.OneWayFareApi
import jp.co.interprism.training.DDD3.jr.pricing.api.one.way.OneWayFareRequest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class CalculationSpec extends Specification {
    @Shared
            today = "20200902"

    def "降車駅が#endStation, 席が#seatの片道料金が#result円"() {
        setup:
        def request = new OneWayFareRequest(
                endStationForm: EndStationForm.valueOf(endStation),
                boardingDateForm: new BoardingDateForm(date),
                seatForm: SeatForm.valueOf(seat),
                superExpressNameForm: SuperExpressNameForm.valueOf(name),
                ageForm: AgeForm.valueOf(age)
        )

        when:
        def response = new OneWayFareApi().invoke(request)

        then:
        assert response.get("片道料金") == result

        where:
        endStation | date  | seat  | name  | age   || result
        "新大阪"      | today | "指定席" | "のぞみ" | "おとな" || 14720
        "新大阪"      | today | "自由席" | "ひかり" | "おとな" || 13870
    }
}
