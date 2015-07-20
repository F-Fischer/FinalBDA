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

@WebServlet("/fexpirada")
public class Fexpirada extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Fexpirada() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nick = request.getParameter("nick");
		String oldPassword = request.getParameter("oldPassword");
		String password = request.getParameter("password");
		String passwordRepetida = request.getParameter("passwordRepetida");

		if (nick == null)
			nick = "";
		if (oldPassword == null)
			oldPassword = "";
		if (password == null)
			password = "";
		if (passwordRepetida == null)
			passwordRepetida = "";

		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		Usuario u;

		try {
			u = uDAO.cargar(nick);
			JSONObject resultado = new JSONObject();
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("GMT"));
			cal.add(Calendar.DAY_OF_YEAR, 7); // una semana mas
			Date nuevaFecha = cal.getTime();

			int status = 0;
			String msg = "OK";

			try {

				if (u == null || !u.getPassword().equals(oldPassword)) {
					status = -1;
					msg = "Usuario o contraseña incorrecta";
					System.out.println("Usuario o contraseña incorrecta");
				} else if (!password.equals(passwordRepetida)) {
					status = -1;
					msg = "Las dos contraseñas son diferentes";
					System.out.println("Las dos contraseñas son diferentes");
				} else {
					u.setPassword(password);
					u.setExpira(nuevaFecha);
					uDAO.modificar(u);
					resultado.put("url", "/index.html");

					HttpSession s = request.getSession(true);
					s.setAttribute(Constantes.USUARIO, u);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			System.out.println("Todo con exito, armando resultado");
			JSONObject respuesta = Util.armarResultado(status, msg, resultado);
			response.setContentType("application/json");
			response.getWriter().print(respuesta.toString());

			System.out.println(respuesta);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
