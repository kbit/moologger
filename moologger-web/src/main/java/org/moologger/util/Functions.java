package org.moologger.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Functions {

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}