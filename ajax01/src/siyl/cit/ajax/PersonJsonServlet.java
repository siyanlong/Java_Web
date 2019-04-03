package siyl.cit.ajax;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.model.Person;
import siyl.cit.util.JsonUtil;

/**
 * Servlet implementation class PersonServlet
 */
public class PersonJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<Integer, List<Person>> persons = new HashMap<Integer, List<Person>>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonJsonServlet() {
		super();
		List<Person> ps = new ArrayList<Person>();
		ps.add(new Person(1, "张三", 1200, 22));
		ps.add(new Person(2, "李四", 1300, 23));
		ps.add(new Person(3, "王五", 1400, 24));
		persons.put(1, ps);
		ps = new ArrayList<Person>();
		ps.add(new Person(11, "刘德华", 12000, 32));
		ps.add(new Person(12, "周杰伦", 13000, 33));
		ps.add(new Person(13, "张曼玉", 14000, 34));
		ps.add(new Person(14, "梁朝伟", 15000, 35));
		persons.put(2, ps);

		ps = new ArrayList<Person>();
		ps.add(new Person(21, "孙悟空", 22000, 132));
		ps.add(new Person(22, "猪八戒", 23000, 133));
		ps.add(new Person(23, "沙和尚", 24000, 134));
		ps.add(new Person(24, "唐和尚", 25000, 135));
		persons.put(3, ps);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、要返回json需要设置为text/html，需要为respose设置contentType
		response.setContentType("text/html;charset=utf-8");
		// 2、获取Writer
		Writer out = response.getWriter();
		// 3、获取部门id
		int did = Integer.parseInt(request.getParameter("did"));
		// 4、获取用户列表对象，
		List<Person> ps = persons.get(did);
		out.write(JsonUtil.getInstance().objToJson(ps));
		// 5、拼接json
		/*
		 * StringBuffer px = new StringBuffer(); px.append("["); int index = 0; for
		 * (Person p : ps) { if (index == 0) { px.append("{"); index++; } else {
		 * px.append(",{"); } px.append("id:").append(p.getId()).append(",");
		 * px.append("name:\"").append(p.getName()).append("\",");
		 * px.append("salary:").append(p.getSalary()).append(",");
		 * px.append("age:").append(p.getAge()); px.append("}"); } px.append("]"); //
		 * 6、通过out写回去 out.write(px.toString());
		 */
	}

}
