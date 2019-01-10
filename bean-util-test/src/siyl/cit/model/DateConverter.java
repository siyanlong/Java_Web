package siyl.cit.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 该方法中的两个参数，第一个参数表示要转换的类型，如果要拷贝一个字符串到日期中，此时这个类型 就是日期类型，第二个参数表示要转换的值
	 * 该方法的返回值就是转换之后的值
	 */
	@Override
	public Object convert(Class cls, Object value) {
		// 判断要转换的类型是否符合要求
		if (cls != Date.class) {
			return null;
		}

		try {
			if (value instanceof String) {
				System.out.println((String) value);
				return sdf.parse((String) value);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

}
