package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.dao.GrupoDAO;

public class TestGrupoDAOEliminar {
	
	public static void main(String[] args) throws SQLException {
		
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		Grupo g = new Grupo();
		
		g.setIdGrupo(1);
		
		boolean resultado = gDAO.eliminar(g);
		
		System.out.println(resultado);
	}

}
