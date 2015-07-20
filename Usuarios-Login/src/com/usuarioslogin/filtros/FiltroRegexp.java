package com.usuarioslogin.filtros;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
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
public class FiltroRegexp implements Filter {

    public FiltroRegexp() {
    }

	public void destroy() {
	}

	private boolean autenticacionHabilitada = true;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (!autenticacionHabilitada) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;


		HttpSession s = req.getSession(false);

		boolean puedeAcceder = s != null
				&& s.getAttribute(Constantes.USUARIO) != null
				&& s.getAttribute(Constantes.USUARIO) instanceof Usuario;

		ServletContext cont=req.getServletContext();
		
		String regs=cont.getInitParameter("filterRegex");
		String regexps[]=regs.toLowerCase().split(",");
		
		String localUri=req.getRequestURI().replaceAll("^/.*?/", "/");
		for (String regexp : regexps) {
			if (Pattern.matches(regexp, localUri)){
				puedeAcceder = true;
			}
			
		}

		if (puedeAcceder) {
			res.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			chain.doFilter(request, response);
			return;
		}
		res.sendRedirect("login.html");

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
