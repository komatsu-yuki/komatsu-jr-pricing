package jp.co.interprism.training.DDD3;

import jp.co.interprism.training.DDD3.jr.pricing.api.form.*;
import jp.co.interprism.training.DDD3.jr.pricing.api.one.way.OneWayFareApi;
import jp.co.interprism.training.DDD3.jr.pricing.api.one.way.OneWayFareRequest;
import jp.co.interprism.training.DDD3.jr.pricing.api.round.trip.RoundTripFareApi;
import jp.co.interprism.training.DDD3.jr.pricing.api.round.trip.RoundTripFareRequest;

public class CalculationTest {
    public static void main(String[] args) {
        BoardingDateForm today = new BoardingDateForm("20200901");

        OneWayFareApi oneWayFareApi = new OneWayFareApi();
        RoundTripFareApi roundTripFareApi = new RoundTripFareApi();

        OneWayFareRequest request1 = new OneWayFareRequest();
        request1.setEndStationForm(EndStationForm.新大阪);
        request1.setBoardingDateForm(today);
        request1.setSuperExpressNameForm(SuperExpressNameForm.のぞみ);
        System.out.println(oneWayFareApi.invoke(request1));//特急料金=5,810円

        OneWayFareRequest request2 = new OneWayFareRequest();
        request2.setEndStationForm(EndStationForm.新大阪);
        request2.setBoardingDateForm(today);
        request2.setSeatForm(SeatForm.自由席);
        System.out.println(oneWayFareApi.invoke(request2));//特急料金=4,960円

        OneWayFareRequest request3 = new OneWayFareRequest();
        request3.setEndStationForm(EndStationForm.新大阪);
        request3.setBoardingDateForm(today);
        request3.setAgeForm(AgeForm.こども);
        System.out.println(oneWayFareApi.invoke(request3));//合計=7,190円

        RoundTripFareRequest request4 = new RoundTripFareRequest();
        request4.setEndStationForm(EndStationForm.姫路);
        request4.setBoardingDateForm(today);
        System.out.println(roundTripFareApi.invoke(request4));//運賃=18,000円
    }
}
