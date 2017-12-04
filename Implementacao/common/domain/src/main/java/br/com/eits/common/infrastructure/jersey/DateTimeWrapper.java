package br.com.eits.common.infrastructure.jersey;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

 
public class DateTimeWrapper {

	
	private static final String STRING_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	private final LocalDateTime dateTime;

	/**
	 *
	 * @param timestamp
	 */
	public DateTimeWrapper( String timestamp )
	{
		this.dateTime = LocalDateTime.parse( timestamp, DateTimeFormatter.ofPattern( STRING_PATTERN ) );
	}

	/**
	 *
	 * @param localDateTime
	 */
	public DateTimeWrapper( LocalDateTime localDateTime )
	{
		this.dateTime = localDateTime;
	}

	/**
	 *
	 * @param calendar
	 */
	public DateTimeWrapper( Calendar calendar )
	{
		this.dateTime = ((GregorianCalendar) calendar).toZonedDateTime().toLocalDateTime();
	}

	/**
	 *
	 * @return
	 */
	public Calendar toCalendar()
	{
		return GregorianCalendar.from( this.dateTime.atZone( ZoneId.systemDefault() ) );
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime toLocalDateTime()
	{
		return dateTime;
	}

	/**
	 *
	 * @return
	 */
	public String toString()
	{
		return dateTime.format( DateTimeFormatter.ofPattern( STRING_PATTERN ) );
	}

	/**
	 *
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o)
	{
		return o == this || o instanceof DateTimeWrapper && ((DateTimeWrapper) o).toLocalDateTime().equals( this.dateTime );
}
	
}
