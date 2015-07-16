package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.dao.GrupoDAO;

public class TestGrupoDAOCargar {
	
	public static void main(String[] args) throws SQLException {
		
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		Grupo g;
		
		g = gDAO.cargar(2);
		
		System.out.println(g.getPermisos());
		//It works viejaa!!
	}

}
