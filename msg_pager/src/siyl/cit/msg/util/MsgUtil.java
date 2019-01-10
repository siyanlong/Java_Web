package siyl.cit.msg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgUtil {
	private static final String dateFormat = "yy/MM/dd HH:mm";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

	public static String formatDate(Date date) {
		return sdf.format(date);
	}
}
