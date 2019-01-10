package siyl.cit.shopping.model;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface ValidateForm {
	/**
	 * type表示验证的类型（1表示不能为空，2表示长度
	 */
	public ValidateType type();

	public String errorMsg();

	public String value() default "";
}
