package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.PressaoArterial;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.singleton.ConnectionManager;

public class OraclePressaoArterialDAO implements PressaoArterialDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(PressaoArterial pressaoArterial) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_htk_pressao_arterial "
				   + "VALUES (SQ_HTK_PRESSAO_ARTERIAL.nextval, ?, ?, ?, ?)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, pressaoArterial.getUsuario().getCodigo());
			pstmt.setInt(2, pressaoArterial.getSistolica());
			pstmt.setInt(3, pressaoArterial.getDiastolica());
			java.sql.Date data = new java.sql.Date(pressaoArterial.getDataRegistro().getTimeInMillis());
			pstmt.setDate(4, data);
			
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
	public void atualizar(PressaoArterial pressaoArterial) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE t_htk_pressao_arterial SET VL_SISTOLICA = ?, VL_DIASTOLICA = ?, DT_REGISTRO = ? "
				   + "WHERE CD_REGISTRO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, pressaoArterial.getSistolica());
			pstmt.setInt(2, pressaoArterial.getDiastolica());
			java.sql.Date data = new java.sql.Date(pressaoArterial.getDataRegistro().getTimeInMillis());
			pstmt.setDate(3, data);
			pstmt.setInt(4, pressaoArterial.getCodigo());
			
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
	public PressaoArterial buscar(int codigo) {
		PressaoArterial pressoArterial = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM t_htk_pressao_arterial WHERE CD_REGISTRO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				pressoArterial = new PressaoArterial(rs.getInt("CD_REGISTRO"), 
													 new Usuario(rs.getInt("CD_USUARIO")), 
													 data, 
													 rs.getInt("VL_SISTOLICA"),
													 rs.getInt("VL_DIASTOLICA"));
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
		
		return pressoArterial;
	}
	
	@Override
	public List<PressaoArterial> listar() {
		List<PressaoArterial> lista = new ArrayList<PressaoArterial>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_pressao_arterial";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			stmt = conexao.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				lista.add(new PressaoArterial(rs.getInt("CD_REGISTRO"), new Usuario(rs.getInt("CD_USUARIO")), 
						                      data, rs.getInt("VL_SISTOLICA"), rs.getInt("VL_DIASTOLICA")));
			}
			
		} catch (SQLException e ) {
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
		String sql = "DELETE FROM t_htk_pressao_arterial WHERE CD_REGISTRO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);
			pstmt.executeUpdate();
		
		} catch (SQLException e ) {
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
