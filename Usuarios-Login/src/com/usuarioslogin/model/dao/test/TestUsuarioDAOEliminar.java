package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Usuario;
import com.usuarioslogin.model.dao.UsuarioDAO;

public class TestUsuarioDAOEliminar {

	public static void main(String[] args) throws SQLException {
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		Usuario u = new Usuario();
		
		u.setIdUsuario(2);
		
		boolean resultado;
		
		resultado = uDAO.eliminar(u);
		System.out.println(resultado);
		
	}

}
