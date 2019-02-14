package siyl.cit.spring.porxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("logAspect") // 让这个切面类被Spring所管理
@Aspect // 声明这个类是切面类
public class LogAspect {

	/**
	 * execution(* siyl.cit.spring.dao.*.add*(..)) 第一个*表示任意返回值
	 * 第二个*表示siyl.cit.spring.dao包中的所有类 第三个*表示以add开头的所有方法 (..)表示任意参数
	 */
	@Before("execution(* siyl.cit.spring.dao.*.add*(..)) || execution(* siyl.cit.spring.dao.*.update*(..)) || "
			+ "execution(* siyl.cit.spring.dao.*.delete*(..))")
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
	@After("execution(* siyl.cit.spring.dao.*.add*(..)) || execution(* siyl.cit.spring.dao.*.delete*(..)) || "
			+ "execution(* siyl.cit.spring.dao.*.update*(..))")
	public void logEnd(JoinPoint jp) {
		Logger.info("方法调用结束加入日志！");
	}

	/**
	 * 函数调用中执行
	 * 
	 */
	@Around("execution(* siyl.cit.spring.dao.*.add*(..)) || execution(* siyl.cit.spring.dao.*.delete*(..)) || "
			+ "execution(* siyl.cit.spring.dao.*.update*(..))")
	public void logAround(ProceedingJoinPoint pjp) throws Throwable {
		Logger.info("开始在Around中加入日志！");
		pjp.proceed();// 执行程序
		Logger.info("结束在Around中加入日志！");
	}
}
