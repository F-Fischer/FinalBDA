package com.usuarioslogin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Grupo;
import com.usuarioslogin.model.Permiso;
import com.usuarioslogin.model.Usuario;

public class UsuarioDAO {

	private Connection c;

	public UsuarioDAO(Connection c) {
		this.c = c;
	}

	public Usuario guardar(Usuario usuario) throws SQLException {
		if (usuario.getIdUsuario() != -1)
			return usuario;

		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("INSERT INTO usuario (apellido,nombre,mail,nick,password,expira) "
							+ "VALUES (?,?,?,?,?,?)");

			pst.setString(1, usuario.getApellido());
			pst.setString(2, usuario.getNombre());
			pst.setString(3, usuario.getMail());
			pst.setString(4, usuario.getNick());
			pst.setString(5, usuario.getPassword());
			pst.setDate(6, new java.sql.Date(usuario.getExpira().getTime()));
			pst.executeUpdate();

			pst = c.prepareStatement("SELECT LAST_INSERT_ID() AS id");
			ResultSet rs = pst.executeQuery();
			rs.next();
			usuario.setIdUsuario(rs.getInt("id"));
			c.commit();

		} catch (Exception e) {
			c.rollback();
			throw new SQLException(e);
		} finally {
			c.setAutoCommit(true);
		}

		return usuario;
	}

	public boolean modificar(Usuario usuario) throws SQLException {
		if (usuario.getIdUsuario() == -1)
			return false;

		boolean resultado = false;

		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("UPDATE usuario SET apellido=?,nombre=?,mail=?,nick=?,password=?"
							+ " WHERE idUsuario=?");
			pst.setString(1, usuario.getApellido());
			pst.setString(2, usuario.getNombre());
			pst.setString(3, usuario.getMail());
			pst.setString(4, usuario.getNick());
			pst.setString(5, usuario.getPassword());
			//pst.setDate(6, new java.sql.Date(usuario.getExpira().getTime()));
			pst.setInt(6, usuario.getIdUsuario());
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

	public Usuario modificarGuardar(Usuario usuario) throws SQLException {
		if (usuario.getIdUsuario() == -1) {
			return guardar(usuario);
		} else {
			modificar(usuario);
			return usuario;
		}
	}

	public boolean eliminar(Usuario usuario) throws SQLException {
		if (usuario.getIdUsuario() == -1)
			return false;

		boolean resultado = false;

		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c
					.prepareStatement("DELETE FROM usuario WHERE idUsuario=?");
			pst.setInt(1, usuario.getIdUsuario());
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

	public Usuario cargar(int idUsuario) throws SQLException{
		Usuario resultado = null;
		
		try{
			PreparedStatement pst = c.prepareStatement("SELECT * FROM usuario WHERE idUsuario=?");
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();
			//int idUsuario, String apellido, String nombre, String mail, String nick, String password, Date expira
			if(rs.next()){
				resultado = new Usuario(rs.getInt("idUsuario"), rs.getString("apellido"),rs.getString("nombre"),
						rs.getString("mail"),rs.getString("nick"),rs.getString("password"),rs.getDate("expira"),
						listarGrupos(rs.getInt("idUsuario")),listarPermisosDirectos(rs.getInt("idUsuario")));
			}
			
		}catch(Exception e){
			throw new SQLException(e);
		}
		
		return resultado;
	}
	
	public Usuario cargar(String nick) throws SQLException{
		Usuario resultado = null;
		
		try{
			PreparedStatement pst = c.prepareStatement("SELECT * FROM usuario WHERE nick=?");
			pst.setString(1, nick);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				resultado = getUsuarioFromResultSet(rs);
			}
			
		}catch(Exception e){
			throw new SQLException(e);
		}
		return resultado;
	}
	
	private Usuario getUsuarioFromResultSet(ResultSet rs) throws SQLException{
		Usuario u = new Usuario(rs.getInt("idUsuario"), rs.getString("apellido"),rs.getString("nombre"),
				rs.getString("mail"),rs.getString("nick"),rs.getString("password"),rs.getDate("expira"));
		return u;
	}
	
	public List<Usuario> cargar(){
		List<Usuario> resultado = new ArrayList<Usuario>();
		
		try{
			PreparedStatement pst = c.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				resultado.add(new Usuario(rs.getInt("idUsuario"), rs.getString("apellido"),rs.getString("nombre"),
						rs.getString("mail"),rs.getString("nick"),rs.getString("password"),rs.getDate("expira"),
						listarGrupos(rs.getInt("idUsuario")),listarPermisosDirectos(rs.getInt("idUsuario"))));
			}
		}catch(Exception e){
			
		}
		
		return resultado;
	}

	public List<Grupo> listarGrupos(int idUsuario) throws SQLException {
		List<Grupo> resultado = new ArrayList<Grupo>();
		try {
			PreparedStatement pst = c
					.prepareStatement("SELECT g.idGrupo, g.nombre, g.descripcion"
							+ " FROM grupo as g, grupo_x_usuario as gxu"
							+ " WHERE g.idGrupo=gxu.idGrupo"
							+ " AND gxu.idUsuario=?");
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();
			GrupoDAO gDAO = new GrupoDAO(Conexion.getConnection());

			while (rs.next()) {
				resultado.add(new Grupo(rs.getInt("idGrupo"), rs
						.getString("nombre"), rs.getString("descripcion"), gDAO
						.listarPermisos(rs.getInt("idGrupo"))));
			}
		} catch (Exception e) {

			throw new SQLException(e);
		}
		return resultado;
	}
	
	

	public List<Permiso> listarPermisosDirectos(int idUsuario)
			throws SQLException {
		List<Permiso> resultado = new ArrayList<Permiso>();
		try {
			PreparedStatement pst = c
					.prepareStatement("SELECT p.idPermiso,p.nombre,p.descripcion"
							+ " FROM usuario_x_permiso as uxp, permiso as p"
							+ " WHERE p.idPermiso=uxp.idPermiso"
							+ " AND uxp.idUsuario=?");
			pst.setInt(1, idUsuario);
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
	
	public boolean agregarPermiso(Usuario usuario,Permiso permiso) throws SQLException {
		if (usuario.getIdUsuario() == -1 || permiso.getIdPermiso() == -1)
			return false;

		boolean resultado = false;
		try {
			c.setAutoCommit(false);
			PreparedStatement pst = c.
					prepareStatement("INSERT INTO usuario_x_permiso (idUsuario,idPermiso) VALUES(?,?)");
			pst.setInt(1, usuario.getIdUsuario());
			pst.setInt(2, permiso.getIdPermiso());
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
	
	public ResultSet todoToRs() throws SQLException {
		ResultSet resultado = null;
		try {
			PreparedStatement pst = c
					.prepareStatement("SELECT * FROM usuario");
			resultado = pst.executeQuery();
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return resultado;
	}

}
