package siyl.cit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharEncodingFilter implements Filter {
	
	private String encoding;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Before Filter");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		System.out.println("End Filter");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String str = config.getInitParameter("encoding");
		if (str == null || "".equals(str.trim())) {
			encoding = "UTF-8";
		}
		else {
			encoding = str;
		}
	}

}
