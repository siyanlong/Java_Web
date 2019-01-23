package siyl.cit.shopping.util;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;

import siyl.cit.shopping.model.ValidateForm;
import siyl.cit.shopping.model.ValidateType;

public class RequestUtil {
	public final static String PATH = "E:\\MyProject\\Java_Web\\trunk\\shopping\\WebContent";

	public final static String[] ALLOW_FILE = { "jpg", "bmp", "gif", "png" };

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

	@SuppressWarnings("unchecked")
	public static void uploadFile(String fname, String fieldName, byte[] fs, HttpServletRequest req) throws Exception {
		FileOutputStream fos = null;
		try {
			if (fs.length > 0) {
				// 对于IE而言，上传的文件会获取完整的绝对路径，此时就需要仅仅获取绝对路径中的文件名
				String fn = FilenameUtils.getName(fname);
				String ext = FilenameUtils.getExtension(fname);
				boolean flag = checkFile(ext);
				if (flag) {
					fos = new FileOutputStream(PATH + "/img/" + fn);
					fos.write(fs, 0, fs.length);
				} else {
					Map<String, String> errors = (Map<String, String>) req.getAttribute("errors");
					errors.put(fieldName, "图片类型必须是jpg、bmp、png、gif！");
				}
			}
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	private static boolean checkFile(String ext) {
		for (String str : ALLOW_FILE) {
			if (str.equals(ext.toLowerCase())) {
				return true;
			}
		}

		return false;
	}
}
