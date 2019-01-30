package siyl.cit.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import siyl.cit.model.User;
import siyl.cit.model.UserException;

//这里写上@RequestMapping相当于所有要访问UserController的都必须写上前缀/user
@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String, User> users = new HashMap<String, User>();

	public UserController() {
		users.put("luxm", new User("luxm", "123", "luxiaoming", "xxx"));
		users.put("siyl", new User("siyl", "123", "siyanlong", "xxx"));
		users.put("lichl", new User("lichl", "123", "lichunlong", "xxx"));
		users.put("dugd", new User("dugd", "123", "duguodong", "xxx"));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", users);
		return "user/list";
	}

	// 连接到add页面时是GET请求，会访问这段代码
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		// 开启modelDriven
		model.addAttribute(new User());
		return "user/add";
	}

	// 开启modelDriven的第二种方法
	// 连接到add页面时是GET请求，会访问这段代码
	// @RequestMapping(value = "/add", method = RequestMethod.GET)
	// public String add(@ModelAttribute("user") User user) {
	// return "user/add";
	// }

	// 在具体添加用户时是POST请求，会访问这段代码
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated User user, BindingResult br, MultipartFile[] attachs, HttpServletRequest req)
			throws IOException {// 一定要紧跟@Validated写验证结果，否则就会报错
		if (br.hasErrors()) {
			// 如果有错误，直接返回到add视图
			return "user/add";
		}
		String realPath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realPath);
		for (MultipartFile attach : attachs) {
			if (attach.isEmpty()) {
				continue;
			}
			File file = new File(realPath + "/" + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), file);
			System.out.println(attach.getName() + "," + attach.getOriginalFilename() + "," + attach.getContentType());
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}

	// 使用@PathVariable告诉框架使用路径中的一个变量作为参数
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String show(@PathVariable String username, Model model) {
		model.addAttribute(users.get(username));
		return "user/show";
	}

	@RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
	public String update(@PathVariable String username, Model model) {
		model.addAttribute(users.get(username));
		return "user/update";
	}

	@RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
	public String update(@PathVariable String username, @Validated User user, BindingResult br) {
		if (br.hasErrors()) {
			return "user/update";
		}
		users.put(username, user);
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/{username}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String username) {
		users.remove(username);
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		if (!users.containsKey(username)) {
			throw new UserException("用户名不存在！");
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			throw new UserException("用户密码不正确！");
		}
		session.setAttribute("loginUser", user);
		return "redirect:/user/users";
	}

	// 局部异常处理，仅仅只能处理这个类中的异常
	// 全局处理异常的方式在springMVC-servlet.xml中配置
	// @ExceptionHandler(value = { UserException.class })
	// public String handlerException(UserException ex, HttpServletRequest req) {
	// req.setAttribute("exception", ex);
	// return "error";
	// }
}
