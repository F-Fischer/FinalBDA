package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.dao.GrupoDAO;

public class TestGrupoDAOCargarSinFiltro {

	public static void main(String[] args) throws SQLException {
		
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		grupos = gDAO.cargar();
		
		System.out.println(grupos);
		
	}
}
