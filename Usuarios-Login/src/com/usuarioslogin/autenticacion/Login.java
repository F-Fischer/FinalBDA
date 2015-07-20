package com.usuarioslogin.autenticacion;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.general.Constantes;
import com.usuarioslogin.general.Util;
import com.usuarioslogin.model.Usuario;
import com.usuarioslogin.model.dao.UsuarioDAO;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {

		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		
		if(nick == null)
			nick = "";
		if(password == null)
			password = "";
		
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		
		try{
			Usuario u = uDAO.cargar(nick);
			String passwordBD = u.getPassword();
			
			JSONObject resultado = new JSONObject();
			int status = 0;
			String msg = "OK";
			
			try{
				if(u == null || !passwordBD.equals(password)){
					status = -1;
					msg = "Usuario o contraseña incorrecta.";
				}
				else
				{
					Calendar cal = Calendar.getInstance();
					cal.setTimeZone(TimeZone.getTimeZone("GMT"));
					
					Date fechaActual = cal.getTime();
					Date fechaUsuario = u.getExpira();
					
					if(fechaActual.compareTo(fechaUsuario)==1){
						
						msg = "Contraseña expirada";
						resultado.put("url", "/fexpirada.html");
					} else {
						
						String url = "/index.html";
						resultado.put("url", url);
						
						HttpSession s = request.getSession(true);
						s.setAttribute(Constantes.USUARIO, u);
					}
				}
				
				
			}catch(JSONException e1){
				e1.printStackTrace();
			}
			
			JSONObject respuesta = Util.armarResultado(status, msg, resultado);
			response.setContentType("application/json");
			response.getWriter().print(respuesta.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
