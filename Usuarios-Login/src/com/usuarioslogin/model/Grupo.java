package com.usuarioslogin.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Grupo {

	private int idGrupo=-1;

	private String nombre;
	private String descripcion;
	private List<Permiso> permisos;

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Grupo(int idGrupo, String nombre, String descripcion,
			List<Permiso> permisos) {

		super();
		this.idGrupo = idGrupo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.permisos = permisos;
	}
	
	public Grupo(int idGrupo, String nombre, String descripcion) {
		super();
		this.idGrupo = idGrupo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.permisos = null;
	}
	
	public Grupo(){
		super();
	}
	
	@Override
	public String toString() {
		
		JSONObject j = new JSONObject();
		
		try{
		
			j.put("idGrupo", getIdGrupo());
			j.put("nombre", getNombre());
			j.put("descripcion", getDescripcion());
			
		}catch(JSONException e){ 
			
			e.printStackTrace();
			
		}
		
		return j.toString();
	
	}
	
	public boolean tienePermiso(Permiso permiso){
		return permisos.contains(permiso);
	}

}
