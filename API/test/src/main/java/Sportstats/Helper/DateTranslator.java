package Sportstats.Helper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import Sportstats.Exception.DateTranslatorException;

/**
 * This class is used to convert back and forward between {@link LocalDate},
 * {@link java.util.Date} and {@link java.sql.Date}. This is to make it easy for
 * the user to insert dates via the url or any other interface without having to
 * use long detailed timestamps.
 * 
 * @author Ali Shakeri
 * @version 2019-05-27
 */
public class DateTranslator {

	/**
	 * Takes a date in {@link Date} format and converts it to {@link LocalDate}.
	 * 
	 * @param dateIn
	 * @return {@link LocalDate}
	 */
	public static LocalDate dateConvertToLocalDate(Date dateIn) {
		return new Date(dateIn.getTime()).toLocalDate();
	}

	/**
	 * Takes a date in {@link LocalDate} format and converts it to {@link Date}.
	 * 
	 * @param dateIn
	 * @return {@link Date}
	 */
	public static Date localDateConvertToDate(LocalDate dateIn) {
		return Date.valueOf(dateIn);
	}

	/**
	 * Takes a date in {@link LocalDate} format and converts it to {@link Date}.
	 * gives a more detailed date including time zones.
	 * 
	 * @param localDate
	 * @return {@link Date}
	 */
	public static Date asDate(LocalDate localDate) {
		return (Date) Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Takes a date in {@link String} format ("yyyy-mm-dd") and converts it to
	 * {@link LocalDate} (might be needed to translate URL inputs).
	 * 
	 * @param date
	 * @return {@link LocalDate}
	 */
	public static LocalDate stringConvertToLocalDate(String date) {

		if (checkDateFormat(date))
			return LocalDate.parse(date);
		else
			throw new DateTranslatorException("Date is in invalid format. The correct format is YYYY-MM-DD");
	}

	/**
	 * Takes a date in {@link String} format ("yyyy-mm-dd") and converts it to
	 * {@link Date}
	 * 
	 * @param dateIn
	 * @return {@link Date}
	 */
	public static Date stringConvertTolDate(String dateIn) {

		if (checkDateFormat(dateIn))
			return Date.valueOf(dateIn);
		else
			throw new DateTranslatorException("Date is in invalid format. The correct format is YYYY-MM-DD");
	}

	/**
	 * Takes a {@link LocalDate} and converts it to a {@link java.sql.Date}
	 * 
	 * @param dateToConvert
	 * @return {@link java.sql.Date}
	 */
	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	/**
	 * Checks if the date is given in valid "yyyy-mm-dd" format.
	 * 
	 * @param date the string to check
	 * @return {@code true} if date is given in correct format, {@code false}
	 *         otherwise
	 * @author Lara Aula
	 * @version 2019-05-27
	 */
	private static boolean checkDateFormat(String date) {

		if (date.matches("[1-2][0-9][0-9][0-9]-((0[1-9]|10|11|12))-([0-2][0-9]|30|31)"))
			return true;
		else
			return false;
	}
}