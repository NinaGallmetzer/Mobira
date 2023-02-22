package swe.mobira;

import androidx.room.TypeConverter;

import java.sql.Date;

public class Converters {
    @TypeConverter
    public static Date dateToLong(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long longToDate(Date date) {
        return date == null ? null : date.getTime();
    }
}

