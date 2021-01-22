package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.healthtrack.bean.PeriodoRefeicao;
import br.com.healthtrack.singleton.ConnectionManager;

public class OraclePeriodoRefeicaoDAO implements PeriodoRefeicaoDAO {
	
	private Connection conexao;

	@Override
	public List<PeriodoRefeicao> listar() {
		List<PeriodoRefeicao> lista = new ArrayList<PeriodoRefeicao>();
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "SELECT * FROM t_htk_periodo_refeicao ORDER BY cd_periodo";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  stmt = conexao.createStatement();
		  rs = stmt.executeQuery(sql);
		  
		  while(rs.next()) {
			  lista.add(new PeriodoRefeicao(rs.getInt("cd_periodo"), rs.getString("ds_periodo")));
		  }
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
			  stmt.close();
			  conexao.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}
}
