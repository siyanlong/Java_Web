package siyl.cit.shopping.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import siyl.cit.shopping.model.ValidateForm;
import siyl.cit.shopping.model.ValidateType;

public class RequestUtil {
	public static boolean validate(Class clz, HttpServletRequest req) {
		Field[] fs = clz.getDeclaredFields();
		boolean isValidate = true;
		Map<String, String> errors = (Map<String, String>) req.getAttribute("errors");
		for (Field f : fs) {
			if (f.isAnnotationPresent(ValidateForm.class)) {
				ValidateForm vf = f.getAnnotation(ValidateForm.class);
				ValidateType vt = vf.type();
				if (vt == ValidateType.NotNull) {
					boolean b = validateNotNull(f.getName(), req);
					if (!b) {
						isValidate = false;
						errors.put(f.getName(), vf.errorMsg());
					}
				} else if (vt == ValidateType.Length) {
					boolean b = validateLength(f.getName(), req, vf.value());
					if (!b) {
						isValidate = false;
						errors.put(f.getName(), vf.errorMsg());
					}
				} else if (vt == ValidateType.Number) {
					boolean b = validateNumber(f.getName(), req);
					if (!b) {
						isValidate = false;
						errors.put(f.getName(), vf.errorMsg());
					}
				}
			}
		}
		return isValidate;
	}

	private static boolean validateNumber(String name, HttpServletRequest req) {
		try {
			Double.parseDouble(req.getParameter(name));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private static boolean validateLength(String name, HttpServletRequest req, String value) {
		String v = req.getParameter(name);
		if (v == null || "".equals(v.trim())) {
			return false;
		}
		if (v.length() < Integer.parseInt(value)) {
			return false;
		}
		return true;
	}

	private static boolean validateNotNull(String name, HttpServletRequest req) {
		if (!req.getParameterMap().containsKey(name)) {
			// 表示表单中并没有要验证的值
			return true;
		}
		String v = req.getParameter(name);
		if (v == null || "".equals(v.trim())) {
			return false;
		}
		return true;
	}

	public static Object setParam(Class cls, HttpServletRequest req) {
		Map<String, String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		Object obj = null;
		try {
			obj = cls.newInstance();
			for (String key : keys) {
				BeanUtils.copyProperty(obj, key, params.get(key)[0]);
			}

			return obj;
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}
}
