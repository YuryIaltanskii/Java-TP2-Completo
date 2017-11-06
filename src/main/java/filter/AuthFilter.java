package filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.AuthUserMb;

@WebFilter(filterName="authFilter",urlPatterns="*.xhtml")
public class AuthFilter implements Filter{

	@Inject
	private AuthUserMb authMb;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servReq = (HttpServletRequest) req;
		HttpServletResponse servResp = (HttpServletResponse) resp;
		
		if(servReq.getRequestURI().equals("/home.xhtml") && !authMb.isLogged()){
			servResp.sendRedirect("index.xhtml");
		}else if(servReq.getRequestURI().equals("/createPost.xhtml") && !authMb.isLogged()){
			servResp.sendRedirect("index.xhtml");
		}else {
			chain.doFilter(req, resp);
		} 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}