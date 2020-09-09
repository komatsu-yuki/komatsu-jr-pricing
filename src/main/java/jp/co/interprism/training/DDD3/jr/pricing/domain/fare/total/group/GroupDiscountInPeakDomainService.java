package jp.co.interprism.training.DDD3.jr.pricing.domain.fare.total.group;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.basic.BasicFareYen;
import jp.co.interprism.training.DDD3.jr.pricing.domain.fare.unit.FareRate;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class GroupDiscountInPeakDomainService {
    private static final FareRate DISCOUNT_RATE_IN_PEAK = new FareRate(0.9);//10%引き

    public boolean inPeak(BoardingDate boardingDate) {
        LocalDate localDate = boardingDate.getLocalDate();
        int year = localDate.getYear();
        if (localDate.getMonthValue() == 12) {
            LocalDate peakStartDate = LocalDate.of(year, 12, 20);
            return localDate.isAfter(peakStartDate);
        }
        if (localDate.getMonthValue() == 1) {
            LocalDate peakEndDate = LocalDate.of(year, 1, 11);
            return localDate.isBefore(peakEndDate);
        }
        return false;
    }

    public BasicFareYen calculateBasicFareYen(BasicFareYen basicFareYen) {
        return basicFareYen.times(DISCOUNT_RATE_IN_PEAK);
    }
}

