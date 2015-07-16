package com.usuarioslogin.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {

	private int idUsuario = -1;
	private String nombre;
	private String apellido;
	private String mail;
	private String nick;
	private String password;
	private Date expira;
	private List<Permiso> permisos;
	private List<Grupo> grupos;
	
	public Usuario(){
		super();
	}
	
	public Usuario(int idUsuario, String apellido, String nombre, String mail, String nick, String password, Date expira){
		super();
		this.idUsuario = idUsuario;
		this.apellido = apellido;
		this.nombre = nombre;
		this.mail = mail;
		this.nick = nick;
		this.password = password;
		this.expira = expira;
	}
	
	public Usuario(int idUsuario, String apellido, String nombre, String mail, String nick, String password, Date expira, List<Grupo> grupos, List<Permiso> permisos){
		super();
		this.idUsuario = idUsuario;
		this.apellido = apellido;
		this.nombre = nombre;
		this.mail = mail;
		this.nick = nick;
		this.password = password;
		this.expira = expira;
		this.grupos = grupos;
		this.permisos = permisos;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpira() {
		return expira;
	}

	public void setExpira(Date expira) {
		this.expira = expira;
	}

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@Override
	public String toString() {
		JSONObject j = new JSONObject();
		
		try {
			j.put("nick",getNick());
			j.put("password",getPassword());
			j.put("mail",getMail());
			j.put("idUsuario", getIdUsuario());
			j.put("nombre",getNombre());
			j.put("apellido",getApellido());
			
			String expira = new SimpleDateFormat("yyyy-MM-dd").format(getExpira());
			System.out.println(expira);
			j.put("expira",expira);
			
			j.put("permisos",getPermisos());
			j.put("grupos",getGrupos());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return j.toString();
	}

}
