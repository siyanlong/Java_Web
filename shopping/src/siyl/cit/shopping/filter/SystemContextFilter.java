package siyl.cit.shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import siyl.cit.shopping.model.SystemContext;

public class SystemContextFilter implements Filter {
	int pageSize;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			int pageOffset = 0;
			int pageSize = 15;
			try {
				pageOffset = Integer.parseInt(req.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(req, resp);
		} finally {
			SystemContext.removePageOffset();
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 15;
		}
	}

}