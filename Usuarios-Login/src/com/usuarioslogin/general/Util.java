package com.usuarioslogin.general;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Util {
	public static String htmlFilter(String text) {

		if (text == null)
			return (null);

		char content[] = new char[text.length()];
		text.getChars(0, text.length(), content, 0);
		StringBuilder result = new StringBuilder(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());

	}

	public static JSONArray rsToJSON(ResultSet rs) throws SQLException,
			JSONException {
		JSONArray json = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			int numColumns = rsmd.getColumnCount();
			JSONObject obj = new JSONObject();

			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = rsmd.getColumnName(i);
				if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
					obj.put(column_name, rs.getArray(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
					obj.put(column_name, rs.getBoolean(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
					obj.put(column_name, Util.htmlFilter(rs.getBlob(
							column_name).toString()));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
					obj.put(column_name, rs.getDouble(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
					obj.put(column_name, rs.getFloat(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
					obj.put(column_name, Util.htmlFilter(rs.getNString(
							column_name).toString()));
				} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
					obj.put(column_name, Util.htmlFilter(rs.getString(
							column_name).toString()));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
					obj.put(column_name, rs.getDate(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
					obj.put(column_name, rs.getTimestamp(column_name));
				} else {
					obj.put(column_name, Util.htmlFilter(rs.getObject(
							column_name).toString()));
				}
			}

			json.put(obj);
		}

		return json;
	}

	public static JSONObject armarResultado(int status, String mensaje, JSONArray dato) {
		JSONObject r = new JSONObject();
		try {
			r.put("status", status);
			r.put("msg", mensaje);
			r.put("resultado", dato);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return r;

	}
	public static JSONObject armarMsgStatus(int status, String mensaje) {
		JSONObject r = new JSONObject();
		try {
			r.put("status", status);
			r.put("msg", mensaje);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return r;

	}
	
	public static JSONObject armarResultadoRs(JSONArray dato) {
		JSONObject r = new JSONObject();
		try {
			r.put("resultado", dato);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return r;
	}
	
	public static JSONObject armarResultado(int status, String mensaje, JSONObject dato) {
		JSONObject r = new JSONObject();
		try {
			r.put("status", status);
			r.put("msg", mensaje);
			r.put("resultado", dato);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return r;
	}
	public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.YEAR) +"/" +
                (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.DATE);
    }
	

}