package siyl.cit.msg.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import siyl.cit.msg.model.User;

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
		Map<String, String> errorMsg = (Map<String, String>) request.getAttribute("error");
		if (errorMsg == null) {
			return "";
		}
		String shop = errorMsg.get(field);
		if (shop == null) {
			return "";
		}

		return shop;
	}

	public static boolean checkAdminOrSelf(HttpSession session, int userId) {
		User u = (User) session.getAttribute("loginUser");
		if (u == null)
			return false;
		if (u.getType() == 1)
			return true;
		if (u.getId() == userId)
			return true;
		return false;
	}
}
