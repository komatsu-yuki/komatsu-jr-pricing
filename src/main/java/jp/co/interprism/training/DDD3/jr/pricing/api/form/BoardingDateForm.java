package jp.co.interprism.training.DDD3.jr.pricing.api.form;

import jp.co.interprism.training.DDD3.jr.pricing.domain.boarding.date.BoardingDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class BoardingDateForm {
    private final String value;

    public BoardingDate parse() {
        String format = "yyyyMMdd";
        LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(format));
        return new BoardingDate(localDate);
    }
}
