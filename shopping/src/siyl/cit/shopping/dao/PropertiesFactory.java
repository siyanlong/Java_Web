package siyl.cit.shopping.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import siyl.cit.shopping.util.PropertiesUtil;

public class PropertiesFactory implements IFactoryDaoNew {
	private static IFactoryDaoNew factory = new PropertiesFactory();
	private static Map<String, Object> daos = new HashMap<String, Object>();

	private PropertiesFactory() {

	}

	public static IFactoryDaoNew getInstance() {
		return factory;
	}

	@Override
	public Object createDao(String name) {
		try {
			if (daos.containsKey(name)) {
				return daos.get(name);
			}
			Properties prop = PropertiesUtil.getDaoProp();
			String cn = prop.getProperty(name);
			Object obj = Class.forName(cn).newInstance();
			daos.put(name, obj);
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
