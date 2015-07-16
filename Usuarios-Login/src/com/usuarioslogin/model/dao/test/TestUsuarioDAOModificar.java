package com.usuarioslogin.model.dao.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.model.Usuario;
import com.usuarioslogin.model.dao.UsuarioDAO;

public class TestUsuarioDAOModificar {

	public static void main(String[] args) throws SQLException {
		
		UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		Usuario u = new Usuario();
		
		u.setIdUsuario(1);
		u.setApellido("apellido modificado");
		u.setNombre("nombre modificado");
		u.setMail("mail@mail.com");
		u.setPassword("password");
		u.setNick("nick");
		
		int year = 2003;
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
	    
	    u.setExpira(utilDate);
	    
	    boolean resultado = uDAO.modificar(u);		
	}
}
