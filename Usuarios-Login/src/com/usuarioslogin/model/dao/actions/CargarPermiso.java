package com.usuarioslogin.model.dao.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.dao.PermisoDAO;

/**
 * Servlet implementation class CargarPermiso
 */
@WebServlet("/cargarPermiso")
public class CargarPermiso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CargarPermiso() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PermisoDAO pDAO = new PermisoDAO(Conexion.getConnection());
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Permiso permiso = new Permiso();
		
		permiso.setNombre(nombre);
		permiso.setDescripcion(descripcion);

		try {
			pDAO.guardar(permiso);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
