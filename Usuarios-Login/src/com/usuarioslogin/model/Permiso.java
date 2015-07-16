package com.usuarioslogin.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Permiso {

	private int idPermiso = -1;
	private String nombre;
	private String descripcion;

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
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

	@Override
	public String toString() {
	
		JSONObject j = new JSONObject();
		try{
			j.put("idPermiso", getIdPermiso());
			j.put("nombre", getNombre());
			j.put("descripcion", getDescripcion());
		}catch(JSONException e){
			e.printStackTrace();
		}
		return super.toString();
	}
	
	public Permiso(String json) throws JSONException{
		JSONObject j = new JSONObject(json);
		setIdPermiso(j.getInt("idPermiso"));
		setNombre(j.getString("nombre"));
		setDescripcion(j.getString("descripcion"));
		
	}
	public Permiso(){
		super();
	}
	
	public Permiso(int idPermiso, String nombre, String descripcion){
		this.idPermiso = idPermiso;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
}
