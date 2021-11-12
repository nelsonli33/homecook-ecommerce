package com.homecook.homecookcommon.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface DateMapper
{
    default Date convert(String date)
    {
        if (date == null || date.isEmpty())
        {
            return null;
        }
        return Date.from(ZonedDateTime.parse(date).toInstant());
    }

    default String format(Date date)
    {
        if (date == null)
        {
            return "";
        }
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(date);
    }
}
