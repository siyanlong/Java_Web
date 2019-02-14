package siyl.cit.spring.porxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("logAspect") // 让这个切面类被Spring所管理
public class LogAspect {

	public void logStart(JoinPoint jp) {
		// 得到执行的对象
		System.out.println(jp.getTarget());
		// 得到执行的方法
		System.out.println(jp.getSignature().getName());
		Logger.info("方法调用开始加入日志！");
	}

	/**
	 * 函数调用完成之后执行
	 * 
	 */
	public void logEnd(JoinPoint jp) {
		Logger.info("方法调用结束加入日志！");
	}

	/**
	 * 函数调用中执行
	 * 
	 */
	public void logAround(ProceedingJoinPoint pjp) throws Throwable {
		Logger.info("开始在Around中加入日志！");
		pjp.proceed();// 执行程序
		Logger.info("结束在Around中加入日志！");
	}
}
