package com.kermeth.profilematcher.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static Instant parseInstant(String date) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'Z'").withZone(ZoneId.of("UTC"));
        return ZonedDateTime.parse(date, formatter).toInstant();
    }

}
