package com.usuarioslogin.model.dao.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Usuario;
import com.usuarioslogin.model.dao.UsuarioDAO;

/**
 * Servlet implementation class CargarUsuario
 */
@WebServlet("/cargarUsuario")
public class CargarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CargarUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		String apellido = request.getParameter("apellido");
		String nombre = request.getParameter("nombre");
		String mail = request.getParameter("mail");
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.add(Calendar.DAY_OF_YEAR, 7); // una semana mas
		Date nuevaFecha = cal.getTime();
		
		Usuario usuario = new Usuario();
		System.out.println(apellido);
		System.out.println(nombre);
		System.out.println(mail);
		System.out.println(nick);
		System.out.println(password);
		System.out.println("ESTE ES EL MAIL "+nombre);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setNick(nick);
		usuario.setMail(mail);
		usuario.setPassword(password);
		usuario.setExpira(nuevaFecha);

		try {
			uDAO.guardar(usuario);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
