package siyl.cit.spring.porxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import siyl.cit.spring.model.LogInfo;

/**
 * 1、写一个类实现InvocationHandler接口
 *
 */
public class LogProxy implements InvocationHandler {
	private LogProxy() {

	}

	// 2、创建一个代理对象
	private Object target;

	// 3、创建一个方法来生成对象，这个方法的参数是要代理的对象，getInstacne所返回的对象就是代理对象
	public static Object getInstance(Object object) {
		// 3.1、创建LogProxy对象
		LogProxy proxy = new LogProxy();

		// 3.2、设置这个代理对象
		proxy.target = object;

		// 3.3、通过Proxy的方法创建代理对象，第一个参数是要代理对象的classLoader
		// 第二个参数是要代理对象实现的所有接口，第三个参数是实现类InvocationHandler的对象
		// 此时的result就是一个代理对象，代理的是object
		Object result = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
				proxy);

		return result;
	}

	/**
	 * 当有了代理对象之后，不管这个代理对象执行什么方法，都会调用以下的invoke方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object object = method.invoke(target, args);
		if (method.isAnnotationPresent(LogInfo.class)) {
			LogInfo logInfo = method.getAnnotation(LogInfo.class);
			Logger.info(logInfo.value());
		}
		return object;
	}

}
