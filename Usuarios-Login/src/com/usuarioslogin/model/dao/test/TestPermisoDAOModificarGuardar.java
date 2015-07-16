package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.dao.PermisoDAO;

public class TestPermisoDAOModificarGuardar {

	public static void main(String[] args) throws SQLException {
		
		PermisoDAO pDAO = new PermisoDAO(Conexion.getConnection());
		Permiso p = new Permiso();
		
		p.setNombre("permiso modificarGuardarss");
		p.setDescripcion("Esta es la descripcion mdssssss");
		
		p = pDAO.modificarGuardar(p);
		
		System.out.println(p.getIdPermiso()+p.getNombre()+p.getDescripcion());
		
		Permiso p2 = new Permiso();
		
		p2.setIdPermiso(p.getIdPermiso());
		p2.setNombre("permiso modGuardardsds");
		p2.setDescripcion("modificada mddsds");
		
		p2 = pDAO.modificarGuardar(p2);
		
		System.out.println(p2.getIdPermiso()+p2.getNombre()+p2.getDescripcion());
		
	}
}
