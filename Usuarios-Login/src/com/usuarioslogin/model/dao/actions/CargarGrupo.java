package com.usuarioslogin.model.dao.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.dao.GrupoDAO;

@WebServlet("/cargarGrupo")
public class CargarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CargarGrupo() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Grupo grupo = new Grupo();
		
		grupo.setNombre(nombre);
		grupo.setDescripcion(descripcion);

		try {
			gDAO.guardar(grupo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
