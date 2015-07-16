package com.usuarioslogin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuarioslogin.model.Permiso;

public class PermisoDAO {

	private Connection c;

	public PermisoDAO(Connection c) {
		this.c = c;
	}

	public Permiso guardar(Permiso permiso) throws SQLException {

		if (permiso.getIdPermiso() != -1) {
			return permiso;
		}

		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("INSERT INTO permiso (nombre, descripcion) VALUES (?,?)");

			pst.setString(1, permiso.getNombre());
			pst.setString(2, permiso.getDescripcion());
			pst.executeUpdate();

			pst = c.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = pst.executeQuery();
			rs.next();
			permiso.setIdPermiso(rs.getInt("id"));
			c.commit();
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.setAutoCommit(true);
		}

		return permiso;
	}

	public boolean modificar(Permiso permiso) throws SQLException {
		if (permiso.getIdPermiso() == -1) {
			return false;
		}

		boolean resultado = false;

		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("UPDATE  permiso SET nombre=?, descripcion=? "
							+ "WHERE idPermiso=?");
			pst.setString(1, permiso.getNombre());
			pst.setString(2, permiso.getDescripcion());
			pst.setInt(3, permiso.getIdPermiso());
			pst.executeUpdate();
			c.commit();
			resultado = true;
		} catch (Exception e) {
			c.rollback();
			throw new SQLException(e);
		}finally{
			c.setAutoCommit(true);
		}
		return resultado;
	}
	
	public Permiso modificarGuardar(Permiso permiso) throws SQLException{
		if(permiso.getIdPermiso() == -1){
			return guardar(permiso);
		}
		else {
			modificar(permiso);
			return permiso;
		}
		
		
	}
	
	public boolean eliminar(Permiso permiso) throws SQLException{
		if(permiso.getIdPermiso() == -1){
			return false;
		}
		
		boolean resultado = false;
		
		try{
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("DELETE FROM permiso WHERE idPermiso=?");
			pst.setInt(1, permiso.getIdPermiso());
			pst.executeUpdate();
			c.commit();
			resultado = true;
			
		}catch(Exception e){
			c.rollback();
			throw new SQLException();
		}finally {
			c.setAutoCommit(true);
		}
		
		return resultado;
	}
	
	public List<Permiso> cargar() throws SQLException {
		List<Permiso> resultado = new ArrayList<Permiso>();
		try {
			PreparedStatement pst = c
					.prepareStatement("SELECT * FROM permiso");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				resultado.add( new Permiso(rs.getInt("idPermiso"),
						rs.getString("nombre"), rs.getString("descripcion")));
			}
		} catch (Exception e) {
			
			throw new SQLException(e);
		}
		return resultado;
	}
}
