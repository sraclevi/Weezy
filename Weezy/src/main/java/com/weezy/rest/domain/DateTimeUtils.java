package com.weezy.rest.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtils {

	private static DateTimeZone		TIME_ZONE	= DateTimeZone
														.forID("Africa/Accra");
	public static DateTimeFormatter	FORMATTER	= DateTimeFormat
														.forPattern(
																"yyyy-MM-dd")
														.withZone(TIME_ZONE)
														.withChronology(
																ISOChronology
																		.getInstance(TIME_ZONE));

	public static DateTime getFirstDayOfMonthWithSameHour(DateTime date) {
		DateTime firstDay = new DateTime(date.getYear(), date.getMonthOfYear(),
				1, date.getHourOfDay(), 0, date.getChronology());

		return firstDay;
	}

	public static DateTime parseString(String month) {
		return FORMATTER.parseDateTime(month);
	}

	public static String dateToString(DateTime dateTime) {
		return FORMATTER.print(dateTime);
	}
}
