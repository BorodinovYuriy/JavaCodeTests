package org.example.helpers.utill;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeDiff {

    private static final Log log = LogFactory.getLog(TimeDiff.class);

    public static Long parseAndReturnDiff(String respStringToParse){
        try {
            // 1. Преобразовать respStringToParse в ZonedDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            ZonedDateTime actualDateTime = ZonedDateTime.parse(
                    respStringToParse,
                    formatter.withZone(ZoneId.of("UTC")));
            // 2. Получить текущее время
            ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
            // 3. Получить разницу
            return Math.abs(Duration.between(actualDateTime, currentDateTime).getSeconds());
        } catch (DateTimeParseException e) {
            // Обработка исключения при парсинге
            log.error("Ошибка парсинга даты: " + e.getMessage());
            //System.err.println("Ошибка парсинга даты: " + e.getMessage());
            return null;
        }

    }

}
