package siyl.cit.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharFilter implements Filter {
	
	private String encoding;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		encoding = cfg.getInitParameter("encoding");
		if (encoding == null || "".equals(encoding)) {
			encoding = "UTF-8";
		}
	}

}
