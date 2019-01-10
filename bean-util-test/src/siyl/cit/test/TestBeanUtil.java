package siyl.cit.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.junit.jupiter.api.Test;

import siyl.cit.model.DateConverter;
import siyl.cit.model.Point;
import siyl.cit.model.PointConvert;
import siyl.cit.model.User;

class TestBeanUtil {

	@Test
	void test() {
		User u = new User();
		String value = "张三";
		String key = "username";
		try {
			BeanUtils.copyProperty(u, key, value);
			// 没有的内容不会发生异常，直接跳过了
			BeanUtils.copyProperty(u, "ddd", value);
			BeanUtils.copyProperty(u, "age", 1);
			// 此时无法拷贝日期，因为BeanUtils根本不知道如何来转换日期1988/2/2
			// 这个时候就需要自己定义相应的转换器来完成转换
			/*
			 * 定义转换器的步骤 1、创建一个类让其实现Converter接口 2、覆盖这个接口中的convert方法，在这个方法中实现转换 3、在拷贝属性之前注册转换器
			 */
			ConvertUtils.register(new DateConverter(), Date.class);
			ConvertUtils.register(new PointConvert(), Point.class);
			BeanUtils.copyProperty(u, "born", "1977-12-22");
			BeanUtils.copyProperty(u, "point", "1977,22");
			System.out.println(u.getBorn());
			System.out.println(u.getPoint());
			System.out.println(u.getUsername() + u.getAge());
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
}
