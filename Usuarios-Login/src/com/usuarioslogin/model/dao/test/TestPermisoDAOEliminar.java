package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.dao.PermisoDAO;

public class TestPermisoDAOEliminar {

	public static void main(String[] args) throws SQLException {
		
		PermisoDAO pDAO = new PermisoDAO(Conexion.getConnection());
		Permiso p = new Permiso();
		
		p.setIdPermiso(4);
		
		boolean resultado = pDAO.eliminar(p);
		System.out.println(resultado);
	}
}
