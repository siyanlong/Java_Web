package siyl.cit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import siyl.cit.model.User;
import siyl.cit.service.UserService;

@Controller
//设置访问的URL
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/showUser/{id}")
	public String ShowUser(@PathVariable int id, HttpServletRequest request) {
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		return "showUser";
	}
}
