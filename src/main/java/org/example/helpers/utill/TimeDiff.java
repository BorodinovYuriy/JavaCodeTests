package org.example.helpers.utill;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeDiff {

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
            //  System.err.println("Ошибка парсинга даты: " + e.getMessage());
            return null;
        }

    }
}