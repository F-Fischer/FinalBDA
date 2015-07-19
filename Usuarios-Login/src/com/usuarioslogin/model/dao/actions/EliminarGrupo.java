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


@WebServlet("/eliminarGrupo")
public class EliminarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EliminarGrupo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		Grupo grupo = new Grupo();
		
		int idGrupo = Integer.parseInt(request.getParameter("grupo"));
		
		grupo.setIdGrupo(idGrupo);
		
		try{
			gDAO.eliminar(grupo);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
