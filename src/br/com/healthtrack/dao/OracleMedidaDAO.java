package br.com.healthtrack.dao;

import br.com.healthtrack.bean.Medida;
import br.com.healthtrack.singleton.ConnectionManager;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleMedidaDAO implements MedidaDAO {
	
	Connection conexao;
	
	@Override
	public List<Medida> lista() {
	  List<Medida> lista = new ArrayList<Medida>();
	  Statement stmt = null;
	  ResultSet rs = null;
	  String sql = "SELECT * FROM t_htk_medida ORDER BY cd_medida";
	  
	  conexao = ConnectionManager.getInstance().getConnection();
	  
	  try {
		stmt = conexao.createStatement();
		rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
		  lista.add(new Medida(rs.getInt("cd_medida"), rs.getString("nm_abreviado"), 
				  			   rs.getString("nm_medida")));
		}
		  
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } finally {
		  try {
			stmt.close();
			conexao.close();
			rs.close();
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
 	 
	  return lista;
	}

}
