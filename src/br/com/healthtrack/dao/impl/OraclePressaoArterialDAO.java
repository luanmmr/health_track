package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.PressaoArterial;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.PressaoArterialDAO;
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
	public List<PressaoArterial> listarUsuario(Usuario usuario) {
		List<PressaoArterial> lista = new ArrayList<PressaoArterial>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_pressao_arterial "
				   + "WHERE cd_usuario = ? ORDER BY dt_registro DESC, cd_registro DESC";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, usuario.getCodigo());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				lista.add(new PressaoArterial(rs.getInt("CD_REGISTRO"), usuario, data, 
										      rs.getInt("VL_SISTOLICA"), rs.getInt("VL_DIASTOLICA")));
			}
			
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
		
		return lista;	
	}
	
	@Override
	public List<PressaoArterial> listarUsuario(Usuario usuario, Calendar data) {
		List<PressaoArterial> lista = new ArrayList<PressaoArterial>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String dia = f.format(data.getTime());
		data.add(Calendar.DATE, 1);
		String diaLimite = f.format(data.getTime());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_pressao_arterial "
			       + "WHERE (dt_registro >= ? AND dt_registro < ?) AND (cd_usuario = ?) "
		           + "ORDER BY dt_registro DESC, cd_registro DESC";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, dia);
			pstmt.setString(2, diaLimite);
			pstmt.setInt(3, usuario.getCodigo());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Calendar dataRegistro = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				lista.add(new PressaoArterial(rs.getInt("CD_REGISTRO"), usuario, dataRegistro, 
										      rs.getInt("VL_SISTOLICA"), rs.getInt("VL_DIASTOLICA")));
			}
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			data.add(Calendar.DATE, -1);
			try {
			  pstmt.close();
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
