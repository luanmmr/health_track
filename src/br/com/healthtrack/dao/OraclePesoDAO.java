package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.singleton.ConnectionManager;


public class OraclePesoDAO implements PesoDAO {
	
	private Connection conexao;
	
	
	@Override
	public void cadastrar(Peso peso) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_htk_peso "
				   + "VALUES (SQ_HTK_PESO.nextval, ?, ?, ?)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, peso.getUsuario().getCodigo());
			pstmt.setDouble(2, peso.getPeso());
			java.sql.Date data = new java.sql.Date(peso.getDataRegistro().getTimeInMillis());
			pstmt.setDate(3, data);
			
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
	public void atualizar(Peso peso) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE t_htk_peso "
				   + "SET VL_PESO = ?, DT_REGISTRO = ? WHERE CD_REGISTRO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setDouble(1, peso.getPeso());
			java.sql.Date data = new java.sql.Date(peso.getDataRegistro().getTimeInMillis());
			pstmt.setDate(2, data);
			pstmt.setInt(3, peso.getCodigo());
			
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
	public Peso buscar(int codigo) {
		Peso peso = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM t_htk_peso WHERE CD_REGISTRO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				peso = new Peso(rs.getInt("CD_REGISTRO"), new Usuario(rs.getInt("CD_USUARIO")), 
								data, rs.getDouble("VL_PESO"));
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
		
		return peso;
	}
	
	@Override
	public List<Peso> listarUsuario(Usuario usuario) {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_peso WHERE cd_usuario = ? ORDER BY dt_registro DESC, cd_registro DESC";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, usuario.getCodigo());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				lista.add(new Peso(rs.getInt("CD_REGISTRO"), usuario, data, rs.getDouble("VL_PESO")));
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
	public List<Peso> listarUsuario(Usuario usuario, Calendar data) {
		List<Peso> lista = new ArrayList<Peso>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String dia = f.format(data.getTime());
		data.add(Calendar.DATE, 1);
		String diaLimite = f.format(data.getTime());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_peso "
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
				dataRegistro.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				lista.add(new Peso(rs.getInt("CD_REGISTRO"), usuario, 
								   dataRegistro, rs.getDouble("VL_PESO")));
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
		String sql = "DELETE FROM t_htk_peso WHERE CD_REGISTRO = ?";
		
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
