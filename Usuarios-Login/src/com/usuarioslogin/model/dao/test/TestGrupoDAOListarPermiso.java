package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.dao.GrupoDAO;

public class TestGrupoDAOListarPermiso {
	
	public static void main(String[] args) throws SQLException {
	
		GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());
		List<Permiso> permisos;
		
		permisos = gDAO.listarPermisos(2);
		
		for (Permiso permiso : permisos) {
			System.out.println(permiso.getIdPermiso());
		}
		
		
	}
	
	
	
	

}
