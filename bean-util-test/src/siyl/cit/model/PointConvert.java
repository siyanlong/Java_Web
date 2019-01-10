package siyl.cit.model;

import org.apache.commons.beanutils.Converter;

public class PointConvert implements Converter {

	@Override
	public Object convert(Class clz, Object value) {
		if (clz != Point.class) {
			return null;
		}

		if (value instanceof String) {
			String v = (String) value;
			String[] vs = v.split(",");
			Point p = new Point();
			p.setX(Integer.parseInt(vs[0]));
			p.setY(Integer.parseInt(vs[1]));
			return p;
		}
		return null;
	}

}
