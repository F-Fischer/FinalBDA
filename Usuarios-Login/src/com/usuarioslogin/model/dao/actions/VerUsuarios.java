package com.usuarioslogin.model.dao.actions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.usuarioslogin.conexion.Conexion;
import com.usuarioslogin.general.Util;
import com.usuarioslogin.model.dao.UsuarioDAO;


@WebServlet("/verUsuario")
public class VerUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerUsuarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
UsuarioDAO uDAO = new UsuarioDAO(Conexion.getConnection());
		
		
		try {
			ResultSet rs = uDAO.todoToRs();
			try {
				JSONArray resultado = new JSONArray();
				resultado = Util.rsToJSON(rs);
				
				
				JSONObject respuesta = Util.armarResultadoRs(resultado);
				response.setContentType("application/json");
				response.getWriter().print(respuesta.toString());
				System.out.println(respuesta);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
