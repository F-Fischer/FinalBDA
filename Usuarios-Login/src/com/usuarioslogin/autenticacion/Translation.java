package com.usuarioslogin.autenticacion;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Translation
 */
@WebServlet("/gettranslation" )
public class Translation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Translation() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
				System.out.println("Lo que me haga feliz");
				JSONObject respuesta = new JSONObject();
				System.out.println(request.getParameterValues("requested[]"));
				String requested[]=request.getParameterValues("requested[]");
				String results[] = new String[requested.length];
				Locale locale = request.getLocale();
				ResourceBundle bundle = ResourceBundle.getBundle("Test", locale);
				
				for (int i =0;i<requested.length;i++) {
					results[i]=bundle.getString(requested[i]);
				}
				
				try {
					respuesta.put("result",results);
					respuesta.put("status",0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				response.setContentType("application/json");
				response.getWriter().print(respuesta.toString());
		
	}



}
