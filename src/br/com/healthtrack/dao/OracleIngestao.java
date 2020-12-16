package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.PeriodoRefeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.singleton.ConnectionManager;


public class OracleIngestao implements IngestaoDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Ingestao ingestao) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_htk_ingestao "
				   + "VALUES (SQ_HTK_INGESTAO.nextval, ?, ?, ?, ?, ?)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setInt(1, ingestao.getAlimento().getCodigo());
			pstmt.setInt(2, ingestao.getUsr().getCodigo());
			pstmt.setInt(3, ingestao.getPeriodoRefeicao().getCodigo());
			java.sql.Date data = new java.sql.Date(ingestao.getDataIngestao().getTimeInMillis());
			pstmt.setDate(4, data);
			pstmt.setInt(5, ingestao.getQuantidade());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public void atualizar(Ingestao ingestao) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE t_htk_ingestao SET CD_ALIMENTO = ?, CD_PERIODO = ?, DT_INGESTAO = ?,"
				   + "VL_QUANTIDADE = ? WHERE CD_INGESTAO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setInt(1, ingestao.getAlimento().getCodigo());
			pstmt.setInt(2, ingestao.getPeriodoRefeicao().getCodigo());
			java.sql.Date data = new java.sql.Date(ingestao.getDataIngestao().getTimeInMillis());
			pstmt.setDate(3, data);
			pstmt.setInt(4, ingestao.getQuantidade());
			pstmt.setInt(5, ingestao.getCodigo());
					
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public Ingestao buscar(int codigo) {
		Ingestao ingestao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_ingestao WHERE CD_INGESTAO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_INGESTAO").getTime());
				ingestao = new Ingestao(rs.getInt("CD_INGESTAO"), new Usuario(rs.getInt("CD_USUARIO")), 
									    new Alimento(rs.getInt("CD_ALIMENTO")), 
									    new PeriodoRefeicao(rs.getInt("CD_PERIODO")), data, 
									    rs.getInt("VL_QUANTIDADE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ingestao;
	}
	
	@Override
	public List<Ingestao> listar() {
		List<Ingestao> lista = new ArrayList<Ingestao>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_ingestao "
				   + "INNER JOIN t_htk_alimento ON (t_htk_ingestao.cd_alimento = t_htk_alimento.cd_alimento) "
				   + "INNER JOIN t_htk_usuario ON (t_htk_ingestao.cd_usuario = t_htk_usuario.cd_usuario) "
				   + "INNER JOIN t_htk_periodo_refeicao ON "
				   + "(t_htk_ingestao.cd_periodo = t_htk_periodo_refeicao.cd_periodo)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			stmt = conexao.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_INGESTAO").getTime());
				lista.add(new Ingestao(rs.getInt("CD_INGESTAO"), new Usuario(rs.getInt("CD_USUARIO")), 
									   new Alimento(rs.getInt("CD_ALIMENTO")), 
									   new PeriodoRefeicao(rs.getInt("CD_PERIODO")), data, 
									   rs.getInt("VL_QUANTIDADE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
			
	}
	
	@Override
	public void remover(int codigo) {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM t_htk_ingestao WHERE CD_INGESTAO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
