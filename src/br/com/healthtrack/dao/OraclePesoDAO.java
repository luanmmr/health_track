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
	public List<Peso> listar() {
		List<Peso> lista = new ArrayList<Peso>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_peso";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			stmt = conexao.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("DT_REGISTRO").getTime());
				
				lista.add(new Peso(rs.getInt("CD_REGISTRO"), new Usuario(rs.getInt("CD_USUARIO")), 
								   data, rs.getDouble("VL_PESO")));
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
