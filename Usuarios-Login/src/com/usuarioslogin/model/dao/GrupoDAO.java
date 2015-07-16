package com.usuarioslogin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.Permiso;

public class GrupoDAO {

	private Connection c;

	public GrupoDAO(Connection c) {
		this.c = c;
	}

	public Grupo guardar(Grupo grupo) throws SQLException {
		if (grupo.getIdGrupo() != -1)
			return grupo;

		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("INSERT INTO grupo (nombre, descripcion) VALUES (?,?)");
			pst.setString(1, grupo.getNombre());
			pst.setString(2, grupo.getDescripcion());
			pst.executeUpdate();

			pst = c.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = pst.executeQuery();
			rs.next();
			grupo.setIdGrupo(rs.getInt("id"));
			c.commit();

		} catch (Exception e) {
			c.rollback();
			throw new SQLException(e);
		} finally {
			c.setAutoCommit(true);
		}

		return grupo;
	}

	public boolean modificar(Grupo grupo) throws SQLException {
		if (grupo.getIdGrupo() == -1)
			return false;

		boolean resultado = false;
		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("UPDATE  grupo SET nombre=?, descripcion=? WHERE idGrupo=?");
			pst.setString(1, grupo.getNombre());
			pst.setString(2, grupo.getDescripcion());
			pst.setInt(3, grupo.getIdGrupo());
			pst.executeUpdate();
			c.commit();
			resultado = true;
		} catch (Exception e) {
			c.rollback();
			throw new SQLException(e);
		} finally {
			c.setAutoCommit(true);
		}
		return resultado;
	}

	public Grupo modificarGuardar(Grupo grupo) throws SQLException {
		if (grupo.getIdGrupo() == -1) {
			return guardar(grupo);
		} else {
			modificar(grupo);
			return grupo;
		}
	}

	public boolean eliminar(Grupo grupo) throws SQLException {
		if (grupo.getIdGrupo() == -1)
			return false;

		boolean resultado = false;
		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("DELETE FROM grupo WHERE idGrupo=?");
			pst.setInt(1, grupo.getIdGrupo());
			pst.executeUpdate();
			c.commit();
			resultado = true;
		} catch (Exception e) {
			c.rollback();
			throw new SQLException(e);
		} finally {
			c.setAutoCommit(true);
		}
		return resultado;
	}

	public Grupo cargar(int idGrupo) throws SQLException {
		Grupo resultado = null;
		try {
			PreparedStatement pst = c
					.prepareStatement("SELECT * FROM grupo WHERE idGrupo=?");
			pst.setInt(1, idGrupo);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				resultado = new Grupo(rs.getInt("idGrupo"),
						rs.getString("nombre"), rs.getString("descripcion"),
						listarPermisos(rs.getInt("idGrupo")));
			}
		} catch (Exception e) {

			throw new SQLException(e);
		}
		return resultado;
	}

	public List<Grupo> cargar() throws SQLException {

		List<Grupo> resultado = new ArrayList<Grupo>();

		try {
			PreparedStatement pst = c.prepareStatement("SELECT * FROM grupo");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				resultado.add(new Grupo(rs.getInt("idGrupo"),
						rs.getString("nombre"), rs.getString("descripcion"),
						listarPermisos(rs.getInt("idGrupo"))));
			}

		} catch (Exception e) {
			throw new SQLException(e);
		}

		return resultado;

	}

	public List<Permiso> listarPermisos(int idGrupo) throws SQLException {

		List<Permiso> resultado = new ArrayList<Permiso>();

		try {
			PreparedStatement pst = c
					.prepareStatement("SELECT p.idPermiso,p.nombre,p.descripcion "
							+ "FROM grupo_x_permiso as gxp, permiso as p "
							+ "WHERE p.idPermiso=gxp.idPermiso "
							+ "AND gxp.idGrupo = ?");

			pst.setInt(1, idGrupo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				resultado.add(new Permiso(rs.getInt("idPermiso"), rs
						.getString("nombre"), rs.getString("descripcion")));
			}

		} catch (Exception e) {
			throw new SQLException(e);
		}

		return resultado;
	}

}
