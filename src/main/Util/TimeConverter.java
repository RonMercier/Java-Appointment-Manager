package main.Util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Ron Mercier - 001406973
 *
 * This class holds static methods to handle time conversions needed by the application.
 */
public class TimeConverter {


    /**
     *
     * @param localDateTime Local date time method for time conversion
     * @return returns local date Time
     */
    public static LocalDateTime localToEST(LocalDateTime localDateTime){

        ZonedDateTime toZDT = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime estzdt = toZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        return estzdt.toLocalDateTime();
    }



}
