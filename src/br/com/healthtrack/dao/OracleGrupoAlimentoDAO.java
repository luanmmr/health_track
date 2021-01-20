package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.healthtrack.singleton.ConnectionManager;
import br.com.healthtrack.bean.GrupoAlimento;

public class OracleGrupoAlimentoDAO implements GrupoAlimentoDAO {
	
	Connection conexao;
	
	@Override
	public List<GrupoAlimento> lista() {
		List<GrupoAlimento> lista = new ArrayList<GrupoAlimento>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_grupo_alimento ORDER BY cd_grupo";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  stmt = conexao.createStatement();
		  rs = stmt.executeQuery(sql);
		  
		  while(rs.next()) {
			  lista.add(new GrupoAlimento(rs.getInt("cd_grupo"), rs.getString("nm_grupo")));
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
