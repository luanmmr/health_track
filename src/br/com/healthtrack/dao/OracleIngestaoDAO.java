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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.Medida;
import br.com.healthtrack.bean.PeriodoRefeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;


public class OracleIngestaoDAO implements IngestaoDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Ingestao ingestao) throws DBException {
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
			throw new DBException("Erro ao cadastrar ingestão");
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
	public List<Ingestao> listarDia(Usuario usuario, Calendar data) {
		List<Ingestao> lista = new ArrayList<Ingestao>();
		PreparedStatement pstmt = null;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String dia = f.format(data.getTime());
		data.add(Calendar.DATE, 1);
		String diaLimite = f.format(data.getTime());
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_ingestao "
				   + "INNER JOIN t_htk_alimento ON (t_htk_ingestao.cd_alimento = t_htk_alimento.cd_alimento) "
				   + "INNER JOIN t_htk_medida ON (t_htk_alimento.cd_medida = t_htk_medida.cd_medida) "
				   + "INNER JOIN t_htk_usuario ON (t_htk_ingestao.cd_usuario = t_htk_usuario.cd_usuario) "
				   + "INNER JOIN t_htk_periodo_refeicao ON (t_htk_ingestao.cd_periodo = t_htk_periodo_refeicao.cd_periodo) "
				   + "WHERE (t_htk_ingestao.dt_ingestao >= ? AND t_htk_ingestao.dt_ingestao < ?) "
				   + "AND t_htk_ingestao.cd_usuario = ? "
				   + "ORDER BY t_htk_ingestao.cd_ingestao";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  pstmt = conexao.prepareStatement(sql);
		  pstmt.setString(1, dia);
		  pstmt.setString(2, diaLimite);
		  pstmt.setInt(3, usuario.getCodigo());
		  rs = pstmt.executeQuery();
		  
		  while (rs.next()) {
			  Calendar dataIngestao = Calendar.getInstance();
			  dataIngestao.setTimeInMillis(rs.getDate("dt_ingestao").getTime());
			  PeriodoRefeicao periodo = new PeriodoRefeicao(0, rs.getString("ds_periodo"));
			  Medida medida = new Medida(0, rs.getString("nm_abreviado"), rs.getString("nm_medida"));
			  Alimento alimento = new Alimento(0, rs.getString("nm_alimento"), medida, rs.getDouble("vl_medida"), 
					  						   rs.getDouble("vl_kcal"), null);
			  lista.add(new Ingestao(rs.getInt("cd_ingestao"), null, alimento, periodo, dataIngestao, rs.getInt("vl_quantidade")));
		  }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			data.add(Calendar.DATE, -1);
			try {
				pstmt.close();
				rs.close();
				conexao.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}
	
	@Override
	public double caloriasGanhasDia(Usuario usr, Calendar data) {
		double totalCalorias = 0;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_ingestao "
				   + "INNER JOIN t_htk_alimento ON (t_htk_ingestao.cd_alimento = t_htk_alimento.cd_alimento) "
				   + "WHERE TO_CHAR(dt_ingestao, 'DD/MM/YYYY') = ? AND cd_usuario = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  pstmt = conexao.prepareStatement(sql);
		  pstmt.setString(1, f.format(data.getTime()));
		  pstmt.setInt(2, usr.getCodigo());
		  rs = pstmt.executeQuery();
		  
		  while (rs.next()) {
			  totalCalorias += rs.getDouble("vl_kcal") * rs.getInt("vl_quantidade");
			  System.out.println(totalCalorias);
		  }
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
			  pstmt.close();
			  rs.close();
			  conexao.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalCalorias;
	}
	
	@Override
	public Map<String, Integer> alimentosSegmentoSemanal(Usuario usr) {
		Map<String, Integer> alimentos = new HashMap<String, Integer>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar data = Calendar.getInstance();
		data.setFirstDayOfWeek(Calendar.SUNDAY);
		data.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String dtInicio = f.format(data.getTime());
		data.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String dtFim = f.format(data.getTime());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT A.nm_alimento, SUM(I.vl_quantidade) CONTAGEM FROM t_htk_ingestao I "
			       + "INNER JOIN t_htk_alimento A ON (I.cd_alimento = A.cd_alimento) "
			       + "WHERE (I.dt_ingestao BETWEEN TO_DATE('"+ dtInicio +"', 'DD/MM/YYYY') AND "
			       								+ "TO_DATE('"+ dtFim +" 11:59:59', 'DD/MM/YYYY HH:MI:SS')) "
			       								+ "AND (I.cd_usuario = ?) GROUP BY A.nm_alimento";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  pstmt = conexao.prepareStatement(sql);
		  pstmt.setInt(1, usr.getCodigo());
		  rs = pstmt.executeQuery();
		  
		  while (rs.next()) {
			alimentos.put(rs.getString("NM_ALIMENTO"), rs.getInt("CONTAGEM"));
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
		return alimentos;
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
