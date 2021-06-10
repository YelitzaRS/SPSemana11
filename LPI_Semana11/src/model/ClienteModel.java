package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Tipo_cliente;
import util.MySqlDBConexion;

public class ClienteModel {
	
	public List<Cliente> consultaCliente(String dni ) {
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; // traer la data de la BD
		try {
			
			con = MySqlDBConexion.getConexion();
			
			String sql = "select c.* , t.nombre  from cliente c "
					+ "inner join tipo_cliente t on c.idTipoCliente = t.idTpoCliente "
					+ "where dni =?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1,dni);
			
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Cliente objCliente = null;
			Tipo_cliente objTipoCliente = null;
			
			while(rs.next()){
				
			    objCliente = new Cliente();
			    objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));
							
				
				objTipoCliente = new Tipo_cliente();
			    objTipoCliente.setIdTipoCliente(rs.getInt(6));
			    objTipoCliente.setNombre(rs.getString(7));
			
				
				objCliente.setTipo_cliente(objTipoCliente);
				
				data.add(objCliente);
			}	
	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public List<Cliente> listaCliente() {
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			
      
			
			String sql = "select c.* , t.nombre  from cliente c "
					+ "inner join tipo_cliente t on c.idTipoCliente = t.idTpoCliente";
				
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Cliente objCliente = null;
			Tipo_cliente objTipoCliente = null;
			
			while(rs.next()){
				
			    objCliente = new Cliente();
			    objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));
							
				
				objTipoCliente = new Tipo_cliente();
			    objTipoCliente.setIdTipoCliente(rs.getInt(6));
			    objTipoCliente.setNombre(rs.getString(7));
			
				
				objCliente.setTipo_cliente(objTipoCliente);
				
				data.add(objCliente);
			
				
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (pstm != null) pstm.close();
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return data;
}
}
