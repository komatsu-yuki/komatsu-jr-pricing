package jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BoardingDate {
    private final LocalDate localDate;

    public Season getSeason() {
        if (this.isPeak()) return Season.PEAK;
        if (this.isOffPeak()) return Season.OFF_PEAK;
        return Season.REGULAR;
    }

    //TODO 境界のチェック
    private boolean isPeak() {
        int year = localDate.getYear();
        if (localDate.getMonthValue() == 12) {
            LocalDate peekStartDate = LocalDate.of(year, 12, 25);
            return localDate.isAfter(peekStartDate);
        }
        if (localDate.getMonthValue() == 1) {
            LocalDate peekEndDate = LocalDate.of(year, 1, 10);
            return localDate.isBefore(peekEndDate);
        }
        return false;
    }

    private boolean isOffPeak() {
        int year = localDate.getYear();
        LocalDate offPeekStartDate = LocalDate.of(year, 1, 16);
        LocalDate offPeekEndDate = LocalDate.of(year, 1, 30);

        return localDate.isAfter(offPeekStartDate) && localDate.isBefore(offPeekEndDate);
    }
}
