package siyl.cit.spring.porxy;

import java.util.Date;

public class Logger {
	public static void info(String info) {
		System.out.println(new Date() + "---->" + info);
	}
}
