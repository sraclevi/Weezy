package com.weezy.rest.domain;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<DateTime> {

	private static DateTimeZone		timeZone	= DateTimeZone
														.forID("Africa/Accra");
	public static DateTimeFormatter	FORMATTER	= DateTimeFormat
														.forPattern(
																"yyyy-MM-dd")
														.withZone(timeZone)
														.withChronology(
																ISOChronology
																		.getInstance(timeZone));

	@Override
	public void serialize(DateTime value, JsonGenerator gen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {

		gen.writeString(FORMATTER.print(value));
	}
}