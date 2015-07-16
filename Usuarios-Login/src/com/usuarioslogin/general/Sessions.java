package com.usuarioslogin.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/session")
public class Sessions extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Sessions() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null){
			System.out.println("Creando session...");
			session = request.getSession(true);
			System.out.println(session.getId());
		} else {
			System.out.println("La session ya existía con el id = "+session.getId());
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
