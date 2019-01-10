package siyl.cit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数信息
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		//String address = new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8");
		String[] interests = request.getParameterValues("interest");
		String interest = "";
		
		//设置文档类型及编码
		response.setContentType("text/html;charset=UTF-8");
		
		//获取输出对象并输出内容
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>UserName：" + userName + "</h1>");
		out.println("<h2>Password：" + password + "</h1>");
		out.println("<h2>Sex：" + sex + "</h1>");
		out.println("<h2>Address：" + address + "</h1>");
		for (String item : interests) {
			//interest += new String(item.getBytes("ISO8859-1"),"UTF-8");
			interest += item;
		}
		out.println("<h2>Interest：" + interest + "</h1>");
		out.println("</html></body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
		request.setCharacterEncoding("UTF-8");
		
		//获取参数信息
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest");
		String interest = "";
				
		//设置文档类型及编码
		response.setContentType("text/html;charset=UTF-8");
				
		//获取输出对象并输出内容
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>UserName：" + userName + "</h1>");
		out.println("<h2>Password：" + password + "</h1>");
		out.println("<h2>Sex：" + sex + "</h1>");
		out.println("<h2>Address：" + address + "</h1>");
		for (String item : interests) {
			interest += item;
		}
		out.println("<h2>Interest：" + interest + "</h1>");
	}

}
