package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.Caminhada;
import br.com.healthtrack.bean.Ciclismo;
import br.com.healthtrack.bean.Corrida;
import br.com.healthtrack.bean.EstiloNatacao;
import br.com.healthtrack.bean.GrupoAlimento;
import br.com.healthtrack.bean.Medida;
import br.com.healthtrack.bean.Natacao;
import br.com.healthtrack.bean.RitmoAtividade;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleAtividadeDAO implements AtividadeDAO {
	
	private Connection conexao;
	
	
	@Override
	public void cadastrar(Atividade atividade) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_htk_atividade "
				   + "VALUES (SQ_HTK_ATIVIDADE.nextval, ?, ?, ?, ?, ?)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			
			conexao.setAutoCommit(false);
			
			pstmt = conexao.prepareStatement(sql);			
			pstmt.setInt(1, atividade.getUsuario().getCodigo());
			pstmt.setInt(2, atividade.getRitmo().getCodigo());
			java.sql.Date dataInicio = new java.sql.Date(atividade.getDataInicio().getTimeInMillis());
			pstmt.setDate(3, dataInicio);
			java.sql.Date dataFim = new java.sql.Date(atividade.getDataFim().getTimeInMillis());
			pstmt.setDate(4, dataFim);
			pstmt.setDouble(5, atividade.getKcalPerdida());	
			pstmt.executeUpdate();
			
			
			if (atividade instanceof Caminhada) {
				Caminhada caminhada = (Caminhada) atividade;
				sql = "INSERT INTO t_htk_caminhada "
				    + "VALUES (SQ_HTK_ATIVIDADE.currval, ?)";
				
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, caminhada.getDistancia());
				pstmt.executeUpdate();
				
			} else if (atividade instanceof Ciclismo) {
				Ciclismo ciclismo = (Ciclismo) atividade;
				sql = "INSERT INTO t_htk_ciclismo "
				    + "VALUES (SQ_HTK_ATIVIDADE.currval, ?)";
				
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, ciclismo.getDistancia());
				pstmt.executeUpdate();
				
			}else if (atividade instanceof Corrida) {
				Corrida corrida = (Corrida) atividade;
				sql = "INSERT INTO t_htk_corrida "
				    + "VALUES (SQ_HTK_ATIVIDADE.currval, ?)";
				
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, corrida.getDistancia());
				pstmt.executeUpdate();
				
			} else if (atividade instanceof Natacao) {
				Natacao natacao = (Natacao) atividade;
				sql = "INSERT INTO t_htk_natacao "
				    + "VALUES (SQ_HTK_ATIVIDADE.currval, ?)";
				
				pstmt = conexao.prepareStatement(sql);
				pstmt.setInt(1, natacao.getEstilo().getCodigo());
				pstmt.executeUpdate();
				
			}
				
			conexao.commit();
			
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
	public void atualizar(Atividade atividade) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE t_htk_atividade SET CD_RITMO = ?, HR_INICIO = ?, "
				   + "HR_FIM = ?, VL_KCAL = ? "
				   + "WHERE CD_ATIVIDADE = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			
			conexao.setAutoCommit(false);
			
			pstmt = conexao.prepareStatement(sql);			
			pstmt.setInt(1, atividade.getRitmo().getCodigo());
			java.sql.Date dataInicio = new java.sql.Date(atividade.getDataInicio().getTimeInMillis());
			pstmt.setDate(2, dataInicio);
			java.sql.Date dataFim = new java.sql.Date(atividade.getDataFim().getTimeInMillis());
			pstmt.setDate(3, dataFim);
			pstmt.setDouble(4, atividade.getKcalPerdida());	
			pstmt.setInt(5, atividade.getCodigo());
			pstmt.executeUpdate();
			
			
			if (atividade instanceof Caminhada) {
				Caminhada caminhada = (Caminhada) atividade;
				sql = "UPDATE t_htk_caminhada SET VL_DISTANCIA = ? "
				    + "WHERE CD_ATIVIDADE = ?";
				
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, caminhada.getDistancia());
				pstmt.setInt(2, caminhada.getCodigo());
				pstmt.executeUpdate();
				
			} else if (atividade instanceof Ciclismo) {
				Ciclismo ciclismo = (Ciclismo) atividade;
				sql = "UPDATE t_htk_ciclismo SET VL_DISTANCIA = ? "
					    + "WHERE CD_ATIVIDADE = ?";
					
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, ciclismo.getDistancia());
				pstmt.setInt(2, ciclismo.getCodigo());
				pstmt.executeUpdate();
				
			}else if (atividade instanceof Corrida) {
				Corrida corrida = (Corrida) atividade;
				sql = "UPDATE t_htk_corrida SET VL_DISTANCIA = ? "
					    + "WHERE CD_ATIVIDADE = ?";
					
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, corrida.getDistancia());
				pstmt.setInt(2, corrida.getCodigo());
				pstmt.executeUpdate();
				
			} else if (atividade instanceof Natacao) {
				Natacao natacao = (Natacao) atividade;
				sql = "UPDATE t_htk_natacao SET CD_ESTILO = ? "
					    + "WHERE CD_ATIVIDADE = ?";
					
				pstmt = conexao.prepareStatement(sql);
				pstmt.setDouble(1, natacao.getEstilo().getCodigo());
				pstmt.setInt(2, natacao.getCodigo());
				pstmt.executeUpdate();
				
			}
				
			conexao.commit();
			
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
	public Atividade buscar(int codigo) {
		Atividade atividade = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_TABELA "
				   + "INNER JOIN t_htk_atividade "
				   + "ON (t_htk_TABELA.cd_atividade = t_htk_atividade.cd_atividade) "
				   + "WHERE t_htk_TABELA.cd_atividade = ?";
		String[] tabelas = {"caminhada", "corrida", "ciclismo", "natacao"};
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		
		  for (String table : tabelas) {
			  pstmt = conexao.prepareStatement(sql.replace("TABELA", table));
			  pstmt.setInt(1, codigo);
			  
			  rs = pstmt.executeQuery();
			  
			  if (rs.next()) {
				  if (table.equals("caminhada")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  return atividade = new Caminhada(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
							  						   new Usuario(rs.getInt("CD_USUARIO")),
							  						   rs.getDouble("VL_DISTANCIA"),
							  						   new RitmoAtividade(rs.getInt("CD_RITMO")));
				  
				  } else if (table.equals("corrida")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  return atividade = new Corrida(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
							  						 new Usuario(rs.getInt("CD_USUARIO")),
							  						 rs.getDouble("VL_DISTANCIA"), 
							  						 new RitmoAtividade(rs.getInt("CD_RITMO")));
					  
				  } else if (table.equals("caminhada")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  return atividade = new Caminhada(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
							  						   new Usuario(rs.getInt("CD_USUARIO")),
							  						   rs.getDouble("VL_DISTANCIA"), 
							  						   new RitmoAtividade(rs.getInt("CD_RITMO")));
					  
				  } else if (table.equals("natacao")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  return atividade = new Natacao(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
							  						 new Usuario(rs.getInt("CD_USUARIO")),
							  						 new EstiloNatacao(rs.getInt("CD_ESTILO")), 
							  						 new RitmoAtividade(rs.getInt("CD_RITMO")));			  
				  }
			  }
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
		
		return atividade;
	}
	
	@Override
	public List<Atividade> listar(int codigoCliente) {
		List<Atividade> lista = new ArrayList<Atividade>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_atividade "
				   + "INNER JOIN t_htk_TABELA "
				   + "ON (t_htk_atividade.cd_atividade = t_htk_TABELA.cd_atividade) "
				   + "WHERE CD_USUARIO = ?";
		String[] tabelas = {"caminhada", "corrida", "ciclismo", "natacao"};
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		
		  for (String table : tabelas) {
			  pstmt = conexao.prepareStatement(sql.replace("TABELA", table));
			  pstmt.setInt(1, codigoCliente);
			  
			  rs = pstmt.executeQuery();
			  
			  while (rs.next()) {
				  if (table.equals("caminhada")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  lista.add(new Caminhada(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
							  				  new Usuario(rs.getInt("CD_USUARIO")),
							  				  rs.getDouble("VL_DISTANCIA"),
							  				  new RitmoAtividade(rs.getInt("CD_RITMO"))));
				  
				  } else if (table.equals("corrida")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  lista.add(new Corrida(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
					  						new Usuario(rs.getInt("CD_USUARIO")),
					  						rs.getDouble("VL_DISTANCIA"), 
					  						new RitmoAtividade(rs.getInt("CD_RITMO"))));
					  
				  } else if (table.equals("caminhada")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  lista.add(new Caminhada(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
				  						      new Usuario(rs.getInt("CD_USUARIO")),
				  						      rs.getDouble("VL_DISTANCIA"), 
				  						      new RitmoAtividade(rs.getInt("CD_RITMO"))));
					  
				  } else if (table.equals("natacao")) {
					  Calendar dtInicio = Calendar.getInstance();
					  dtInicio.setTimeInMillis(rs.getDate("HR_INICIO").getTime());
					  Calendar dtFim = Calendar.getInstance();
					  dtFim.setTimeInMillis(rs.getDate("HR_FIM").getTime());
					  lista.add(new Natacao(rs.getInt("CD_ATIVIDADE"), dtInicio, dtFim, 
					  						new Usuario(rs.getInt("CD_USUARIO")),
					  						new EstiloNatacao(rs.getInt("CD_ESTILO")), 
					  						new RitmoAtividade(rs.getInt("CD_RITMO"))));			  
				  }
			  }
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
		
		return lista;
	}
	
	@Override
	public void remover(int codigo) {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM t_htk_atividade WHERE cd_atividade = ?";
		
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
