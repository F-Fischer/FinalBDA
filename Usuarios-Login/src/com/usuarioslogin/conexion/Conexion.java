package com.usuarioslogin.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Connecion
 */
@WebServlet("/Connexion")
public class Conexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Conexion() {
        super();
       
    }

    public static Connection getConnection(){
    	
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bda_final", "root","@@@@()()1234abcd");
    		return con;
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }

}
