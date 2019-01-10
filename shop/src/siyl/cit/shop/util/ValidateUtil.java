package siyl.cit.shop.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ValidateUtil {
	public static boolean validateNull(HttpServletRequest request, String[] fields) {
		boolean validate = true;
		Map<String, String> errorshop = new HashMap<String, String>();
		for (String field : fields) {
			String value = request.getParameter(field);
			if (value == null || "".equals(value.trim())) {
				validate = false;
				errorshop.put(field, field + "不能为空！");
			}
		}
		if (!validate) {
			request.setAttribute("error", errorshop);
		}
		
		return validate;
	}
	
	public static String showError(HttpServletRequest request, String field) {
		Map<String, String> errorshop = (Map<String, String>)request.getAttribute("error");
		if (errorshop == null) {
			return "";
		}
		String shop = errorshop.get(field);
		if (shop == null) {
			return "";
		}
		
		return shop;
	}
}
