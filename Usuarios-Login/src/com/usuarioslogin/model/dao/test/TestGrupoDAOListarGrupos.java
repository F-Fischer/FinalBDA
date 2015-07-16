package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.util.List;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.dao.UsuarioDAO;

public class TestGrupoDAOListarGrupos {

	public static void main(String[] args) throws SQLException {
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());

		List<Grupo> grupos = null;

		grupos = uDAO.listarGrupos(1);

		for (Grupo grupo : grupos) {

			System.out.println(grupo.getIdGrupo());
		}

	}

}
