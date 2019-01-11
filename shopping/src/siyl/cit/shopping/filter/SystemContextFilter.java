package siyl.cit.shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import siyl.cit.shopping.model.SystemContext;
import siyl.cit.shopping.util.RequestUtil;

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
			String sort = req.getParameter("sort");
			String order = req.getParameter("order");
			try {
				pageOffset = Integer.parseInt(req.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}
			SystemContext.setOrder(order);
			SystemContext.setSort(sort);
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(pageSize);
			SystemContext.setRealpath(RequestUtil.PATH);
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