package siyl.cit.util;

import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

public class XStreamUtil {
	private static XStreamUtil util;

	private XStreamUtil() {
	}

	public static XStreamUtil getInstacne() {
		if (util == null) {
			util = new XStreamUtil();
		}
		return util;
	}

	public String objToXml(Object obj, Map<String, Class<?>> alias) {
		XStream stream = new XStream();
		Set<String> keys = alias.keySet();
		for (String key : keys) {
			stream.alias(key, alias.get(key));
		}
		return stream.toXML(obj);
	}

	public Object xmlToObj(String xml, Map<String, Class<?>> alias) {
		XStream stream = new XStream();
		Set<String> keys = alias.keySet();
		for (String key : keys) {
			stream.alias(key, alias.get(key));
		}
		return stream.fromXML(xml);
	}
}
