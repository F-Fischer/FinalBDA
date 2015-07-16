package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.dao.PermisoDAO;

public class TestPermisoDAOCargar {
	
	public static void main(String[] args) throws SQLException {
		
		PermisoDAO pDAO = new PermisoDAO(Conexion.getConnection());
		List<Permiso> permisos = new ArrayList<Permiso>();
		
		permisos = pDAO.cargar();
		
		for (Permiso permiso : permisos) {
			System.out.println(permiso.getIdPermiso()+permiso.getNombre()+permiso.getDescripcion());
			}
		
	}

}
