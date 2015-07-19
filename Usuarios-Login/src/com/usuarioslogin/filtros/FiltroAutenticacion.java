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
public class FiltroAutenticacion implements Filter {

	
	private String[] 
			rnp = "/login,/login.html,/bower_components/jquery/dist/jquery.min.js,/bower_components/bootstrap/dist/js/bootstrap.min.js,/bower_components/metisMenu/dist/metisMenu.min.js,dist/js/sb-admin-2.js,/bower_components/bootstrap/dist/css/bootstrap.min.css,/bower_components/metisMenu/dist/metisMenu.min.css,/dist/css/sb-admin-2.css,/bower_components/font-awesome/css/font-awesome.min.css".
			toLowerCase().split(",");
	
    public FiltroAutenticacion() {
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (!autenticacionHabilitada) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		boolean acceso = acceso(req.getRequestURI());
		//LOG.debug("URI: {}, acceso: {}", req.getRequestURI(), acceso);

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
	}
	
	private boolean acceso(String uri) {
		for (String s : rnp) {
			if (uri.toLowerCase().endsWith(s))

				return true;
		}
		return false;
	}

	private boolean autenticacionHabilitada = true;

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
