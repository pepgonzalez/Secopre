package ideasw.secopre.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase utilireia para manejar todo lo relacionado con tiempo y fecha
 * 
 * @author jorge.canoc@gmail.com
 * 
 */
public class TimeUtils {
	static final Logger LOG = LoggerFactory.getLogger(TimeUtils.class);
	public static final String COMPLETE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String CUSTOM_SIMPLE_DATE_FORMAT = "yyyy/MM/dd";
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CUSTOM_COMPLETE_DATE_FORMAT = "dd-MMM-yy',' HH:mm 'hrs.'";
	public static final SimpleDateFormat completeDateFormat = new SimpleDateFormat(
			COMPLETE_DATE_FORMAT);
	public static final SimpleDateFormat customnCompleteDateFormat = new SimpleDateFormat(
			CUSTOM_COMPLETE_DATE_FORMAT);
	public static final String DB2_DATE_FORMAT = "yyyyMMdd";
	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(
			DEFAULT_DATE_FORMAT);

	/**
	 * Retorna un objeto de tipo fecha dado un valor string y un formateador
	 * para la fecha {@link SimpleDateFormat}, previene la excepcion
	 * {@link ParseException}
	 * 
	 * @param value
	 * @param simpleDateFormat
	 * @return
	 */
	public static Date getDate(String value, SimpleDateFormat simpleDateFormat) {
		Date date = null;
		if (value == null) {
			return null;
		}
		try {
			date = simpleDateFormat.parse(value);

		} catch (ParseException e) {
			LOG.debug("Error al formatear la fecha " + value);
			LOG.error("Error al formatear la fecha " + value, e);
		}
		return date;
	}

	/**
	 * Retorna un objeto de tipo fecha dado un valor en string y un patron de
	 * fecha, previene la excepcion {@link ParseException}
	 * 
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static Date getDate(String value, String pattern) {
		return getDate(value, new SimpleDateFormat(pattern));
	}

	/**
	 * Retorna un objeto de tipo fecha dado un valor en string, previene la
	 * excepcion {@link ParseException}
	 * 
	 * @param value
	 * @return
	 */
	public static Date getDate(String value) {
		return getDate(value, DEFAULT_DATE_FORMAT);
	}

	public static boolean isOverlaps(Interval intervalToValidate,
			List<Interval> intervals) {

		boolean isOverlaps = false;
		for (Interval interval : intervals) {
			if (intervalToValidate.overlaps(interval)) {
				isOverlaps = true;
				return isOverlaps;
			}
		}
		return isOverlaps;
	}
}
