package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.util.List;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Usuario;
import com.usuarioslogin.model.dao.UsuarioDAO;

public class TestUsuarioDAOCargar {
	
	public static void main(String[] args) throws SQLException {
		
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		Usuario u = null;
		List<Usuario> usuarios = null;
		
		
		u = uDAO.cargar("nick");
		
		System.out.println(u.getNombre());
		
		usuarios = uDAO.cargar();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNombre());
		}
		
	}

}
