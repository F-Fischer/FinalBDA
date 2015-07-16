package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.dao.GrupoDAO;

public class TestGrupoDAOGuardar {

	public static void main(String[] args) throws SQLException {
		
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		Grupo g = new Grupo();
		Permiso p = new Permiso();
		
		
		g.setNombre("Nombre grupo 3");
		g.setDescripcion("Descripcion de grupo 4");
		
		gDAO.guardar(g);
		
		System.out.println(g.getIdGrupo());
	}
}
