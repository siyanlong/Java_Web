package siyl.cit.shopping.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import siyl.cit.shopping.dao.IFactoryDao;
import siyl.cit.shopping.dao.IFactoryDaoNew;
import siyl.cit.shopping.model.ShopDi;

public class DaoUtil {
	public static void main(String[] args) {
		System.out.println(createDaoFactory());
	}

	public static void diDao(Object obj) {
		try {
			/**
			 * 获取所有的方法
			 */
			Method[] methods = obj.getClass().getDeclaredMethods();
			for (Method currMethod : methods) {
				/**
				 * 判断方法上面是否有ShopDi的Annotation，有这个Annotation才进行注入
				 */
				if (currMethod.isAnnotationPresent(ShopDi.class)) {
					/*
					 * 获取method上的ShopDi对象
					 */
					ShopDi shopDi = currMethod.getAnnotation(ShopDi.class);
					/**
					 * 获取这个Annotation的值
					 */
					String str = shopDi.value();
					/**
					 * 判断shopDi的value是否为空，如果为空，就等于要使用setXXX这个方法名称 来完成注入
					 */
					if (str == null || "".equals(str)) {
						str = currMethod.getName().substring(3);
						str = str.substring(0, 1).toLowerCase() + str.substring(1);
						System.out.println(str);
					}

					Object currObject = DaoUtil.createDaoFactory_New().createDao(str);
					currMethod.invoke(obj, currObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static IFactoryDaoNew createDaoFactory_New() {
		IFactoryDaoNew f = null;
		try {
			Properties prop = PropertiesUtil.getDaoProp();
			String fs = prop.getProperty("factory");
			Class clz = Class.forName(fs);
			String mn = "getInstance";
			Method m = clz.getMethod(mn);
			f = (IFactoryDaoNew) m.invoke(clz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return f;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static IFactoryDao createDaoFactory() {
		IFactoryDao f = null;
		try {
			Properties prop = PropertiesUtil.getDaoProp();
			String fs = prop.getProperty("factory");
			Class clz = Class.forName(fs);
			String mn = "getInstance";
			Method m = clz.getMethod(mn);
			f = (IFactoryDao) m.invoke(clz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return f;
	}
}
