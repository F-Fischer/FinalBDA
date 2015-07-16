package com.usuarioslogin.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usuarioslogin.general.Constantes;
import com.usuarioslogin.model.Usuario;

@WebFilter("/*")
public class Filtro implements Filter {

	private String[] rnp = "/login,/login.html,/webjars/bootstrap/3.3.4/css/bootstrap.min.css,/webjars/jquery/2.1.3/jquery.min.js,/webjars/jquery/2.1.3/jquery.min.map"
			.toLowerCase().split(",");

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (!autenticacionHabilitada) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		boolean acceso = acceso(req.getRequestURI());
		
		if (acceso) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession s = req.getSession(false);
		
		if (s != null
				&& s.getAttribute(Constantes.USUARIO) instanceof Usuario) {
			chain.doFilter(request, response);
			return;
		}
		
		res.sendRedirect(req.getServletContext().getContextPath()
				+ "/login.html");

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

		autenticacionHabilitada = Boolean
				.parseBoolean(fConfig.getServletContext().getInitParameter("autenticacionHabilitada"));
	}

	private boolean acceso(String uri) {
		for (String s : rnp) {
			if (uri.toLowerCase().endsWith(s))

				return true;
		}
		return false;
	}

	private boolean autenticacionHabilitada = false;

	@Override
	public void destroy() {
	}

}
