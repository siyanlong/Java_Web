package siyl.cit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	// RequestMapping表示用哪个url来对应
	// 如果参数是必须的，就用@RequestParam，如果不是必须的就不用@RequestParam
	// 传递值给View可以使用Map<String, Object>，这种方式不推荐
	@RequestMapping({ "/hello", "/" })
	public String hello(@RequestParam String username, String nickname, Map<String, Object> context) {
		System.out.println("Hello！");
		System.out.println(username);
		System.out.println(nickname);
		context.put("name", "KO");
		return "hello";
	}

	// 传递值给View可以使用Model，推荐使用这种方式
	@RequestMapping("/welcome")
	public String welcome(String nickname, Model model) {
		System.out.println("Welcome！");
		model.addAttribute("name", "KO");
		// 此时用什么作为Key？使用变量的类型作为Key，相当于model.addAttribute("string", "OK");
		// 这种方式在添加自建类的变量时常用，不如：Model.addAttribute(new
		// User());相当于model.addAttribute("user", new User());
		model.addAttribute("OK");
		return "welcome";
	}
}
