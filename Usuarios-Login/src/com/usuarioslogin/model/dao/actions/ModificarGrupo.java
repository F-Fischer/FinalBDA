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

@WebServlet("/modificarGrupo")
public class ModificarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificarGrupo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		Grupo grupo = new Grupo();
		
		int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		
		grupo.setDescripcion(descripcion);
		grupo.setNombre(nombre);
		grupo.setIdGrupo(idGrupo);
		
		try{
			gDAO.modificar(grupo);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
