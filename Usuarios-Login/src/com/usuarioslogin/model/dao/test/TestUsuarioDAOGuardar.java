package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Usuario;
import com.usuarioslogin.model.dao.UsuarioDAO;

public class TestUsuarioDAOGuardar {
	
	public static void main(String[] args) throws SQLException {
		
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());	
		Usuario u = new Usuario();
		
		int year = 2007;
	    int month = 12;
	    int day = 12;

	    String date = year + "/" + month + "/" + day;
	    Date utilDate = null;

	    try {
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	      utilDate = formatter.parse(date);
	      System.out.println("utilDate:" + utilDate);
	    } catch (ParseException e) {
	      System.out.println(e.toString());
	      e.printStackTrace();
	    }
		
		u.setApellido("Expirada 3");
		u.setNombre("Contra");
		u.setMail("mail2@mail.com");
		u.setNick("g");
		u.setPassword("g");
		u.setExpira(utilDate);
		
		uDAO.guardar(u);
	}

}
