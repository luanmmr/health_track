package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.GrupoAlimento;
import br.com.healthtrack.bean.Medida;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleAlimentoDAO implements AlimentoDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Alimento alimento) throws DBException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_htk_alimento "
				   + "VALUES (SQ_HTK_ALIMENTO.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setInt(1, alimento.getGrupoAlimento().getCodigo());
			pstmt.setString(2, alimento.getNome());
			pstmt.setDouble(3, alimento.getKcal());
			pstmt.setDouble(4, alimento.getValorMedida());
			pstmt.setDouble(5, alimento.getCarboidrato());
			pstmt.setDouble(6, alimento.getGorduraTotal());
			pstmt.setDouble(7, alimento.getProteina());
			pstmt.setDouble(8, alimento.getGorduraSaturada());
			pstmt.setDouble(9, alimento.getGorduraTrans());
			pstmt.setDouble(10, alimento.getColesterol());
			pstmt.setDouble(11, alimento.getSodio());
			pstmt.setDouble(12, alimento.getPotassio());
			pstmt.setDouble(13, alimento.getFibraDietetica());
			pstmt.setDouble(14, alimento.getAcucares());
			pstmt.setDouble(15, alimento.getVitaminaA());
			pstmt.setDouble(16, alimento.getVitaminaC());
			pstmt.setDouble(17, alimento.getCalcio());
			pstmt.setDouble(18, alimento.getFerro());
			pstmt.setInt(19, alimento.getMedida().getCodigo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar alimento");
			
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
	public void atualizar(Alimento alimento) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE t_htk_alimento SET CD_GRUPO = ?, NM_ALIMENTO = ?, VL_KCAL = ?, VL_MEDIDA = ?, "
				   + "VL_TOTAL_CARBOIDRATOS = ?, VL_GORDURA_TOTAL = ?, VL_PROTEINA = ?, VL_GORDURA_SATURADA = ?, "
				   + "VL_GORDURA_TRANS = ?, VL_COLESTEROL = ?, VL_SODIO = ?, VL_POTASSIO = ?, "
				   + "VL_FIBRA_DIETETICA = ?, VL_ACUCARES = ?, VL_VITAMINA_A = ?, VL_VITAMINA_C = ?, "
				   + "VL_CALCIO = ?, VL_FERRO = ?, CD_MEDIDA = ? "
				   + "WHERE CD_ALIMENTO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setInt(1, alimento.getGrupoAlimento().getCodigo());
			pstmt.setString(2, alimento.getNome());
			pstmt.setDouble(3, alimento.getKcal());
			pstmt.setDouble(4, alimento.getValorMedida());
			pstmt.setDouble(5, alimento.getCarboidrato());
			pstmt.setDouble(6, alimento.getGorduraTotal());
			pstmt.setDouble(7, alimento.getProteina());
			pstmt.setDouble(8, alimento.getGorduraSaturada());
			pstmt.setDouble(9, alimento.getGorduraTrans());
			pstmt.setDouble(10, alimento.getColesterol());
			pstmt.setDouble(11, alimento.getSodio());
			pstmt.setDouble(12, alimento.getPotassio());
			pstmt.setDouble(13, alimento.getFibraDietetica());
			pstmt.setDouble(14, alimento.getAcucares());
			pstmt.setDouble(15, alimento.getVitaminaA());
			pstmt.setDouble(16, alimento.getVitaminaC());
			pstmt.setDouble(17, alimento.getCalcio());
			pstmt.setDouble(18, alimento.getFerro());
			pstmt.setInt(19, alimento.getMedida().getCodigo());
			pstmt.setInt(20, alimento.getCodigo());
			
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
	public Alimento buscar(int codigo) {
		Alimento alimento = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_alimento "
				   + "INNER JOIN t_htk_grupo_alimento "
				   + "ON (t_htk_alimento.cd_grupo = t_htk_grupo_alimento.cd_grupo) "
				   + "INNER JOIN t_htk_medida "
				   + "ON (t_htk_medida.cd_medida = t_htk_alimento.cd_medida) "
				   + "WHERE cd_alimento = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);		
			pstmt.setInt(1, codigo);	
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Medida medida = new Medida(rs.getInt("CD_MEDIDA"), rs.getString("NM_ABREVIADO"),
										   rs.getString("NM_MEDIDA"));
				GrupoAlimento grupoAlimento = new GrupoAlimento(rs.getInt("CD_GRUPO"), rs.getString("NM_GRUPO"));
				alimento = new Alimento(rs.getInt("CD_ALIMENTO"), rs.getString("NM_ALIMENTO"), 
										medida, rs.getDouble("VL_MEDIDA"), rs.getDouble("VL_KCAL"), 
										grupoAlimento);
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
		
		return alimento;
	}
	
	@Override
	public List<Alimento> listar() {
		List<Alimento> lista = new ArrayList<Alimento>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_alimento "
				   + "INNER JOIN t_htk_grupo_alimento "
				   + "ON (t_htk_alimento.cd_grupo = t_htk_grupo_alimento.cd_grupo) "
				   + "INNER JOIN t_htk_medida "
				   + "ON (t_htk_medida.cd_medida = t_htk_alimento.cd_medida) ";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			stmt = conexao.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Medida medida = new Medida(rs.getInt("CD_MEDIDA"), rs.getString("NM_ABREVIADO"),
										   rs.getString("NM_MEDIDA"));
				GrupoAlimento grupoAlimento = new GrupoAlimento(rs.getInt("CD_GRUPO"), rs.getString("NM_GRUPO"));
				lista.add(new Alimento(rs.getInt("CD_ALIMENTO"), rs.getString("NM_ALIMENTO"), 
									   medida, rs.getDouble("VL_MEDIDA"), rs.getDouble("VL_KCAL"), 
									   grupoAlimento));
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
		String sql = "DELETE FROM t_htk_alimento WHERE CD_ALIMENTO = ?";
		
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
