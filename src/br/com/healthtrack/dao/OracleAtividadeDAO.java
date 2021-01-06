package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.sun.xml.internal.ws.util.StringUtils;

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
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OracleAtividadeDAO implements AtividadeDAO {
	
	private Connection conexao;
	
	
	@Override
	public void cadastrar(Atividade atividade) throws DBException {
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
			
		} catch (SQLException e) {
			try {
				conexao.rollback();
				
			} catch (Exception a) {
				a.printStackTrace();
				throw new DBException("Erro ao fazer um rollback");
				
			}		
			e.printStackTrace();
			throw new DBException("Erro ao registrar atividade");
			
		} finally {
			try {
			  conexao.commit();
		      pstmt.close();
			  conexao.close();
			  
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	@Override
	public void update(Atividade atividade) throws DBException {
	  PreparedStatement pstmt = null;
	  String sql = "UPDATE t_htk_atividade SET cd_ritmo = ?, dt_inicio = ?, dt_fim = ?, vl_kcal = ?"
	  		     + "WHERE cd_atividade = ?";
	  
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
	       Caminhada atv = (Caminhada) atividade;
	       sql = "UPDATE t_htk_caminhada SET vl_distancia = ? WHERE cd_atividade = ?";
	       pstmt = conexao.prepareStatement(sql);
	       pstmt.setDouble(1, atv.getDistancia());
	       pstmt.setInt(2, atv.getCodigo());
	       pstmt.executeUpdate();
	       
	    } else if (atividade instanceof Ciclismo) {
	    	Ciclismo atv = (Ciclismo) atividade;
	        sql = "UPDATE t_htk_ciclismo SET vl_distancia = ? WHERE cd_atividade = ?";
	        pstmt = conexao.prepareStatement(sql);
	        pstmt.setDouble(1, atv.getDistancia());
	        pstmt.setInt(2, atv.getCodigo());
	        pstmt.executeUpdate();
	        
	    } else if (atividade instanceof Corrida) {
	    	Corrida atv = (Corrida) atividade;
	        sql = "UPDATE t_htk_corrida SET vl_distancia = ? WHERE cd_atividade = ?";
	        pstmt = conexao.prepareStatement(sql);
	        pstmt.setDouble(1, atv.getDistancia());
	        pstmt.setInt(2, atv.getCodigo());
	        pstmt.executeUpdate();
	        
	    } else if (atividade instanceof Natacao) {
	    	Natacao atv = (Natacao) atividade;
	        sql = "UPDATE t_htk_natacao SET cd_estilo = ? WHERE cd_atividade = ?";
	        pstmt = conexao.prepareStatement(sql);
	        pstmt.setDouble(1, atv.getEstilo().getCodigo());
	        pstmt.setInt(2, atv.getCodigo());
	        pstmt.executeUpdate();
	        
	    } 
	    
	  } catch (SQLException e) {
		  try {
			conexao.rollback();  
			
		  } catch(Exception a) {
			a.printStackTrace();
			throw new DBException("Erro ao fazer um rollback");
			
		  }  
		  e.printStackTrace();
		  throw new DBException("Erro ao atualizar atividade");
		  
	  } finally {
		  try {
		    conexao.commit();
		    pstmt.close();
		    conexao.close();
		    
		  } catch (SQLException e) {
			  e.printStackTrace();
			  
		  }
	  }
	  
	}
	
	@Override
	public List<Atividade> listaAtividadesDia(int codigoCliente, Calendar data) {
		List<Atividade> lista = new ArrayList<Atividade>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String dia = f.format(data.getTime());
		data.add(Calendar.DATE, 1);
		String diaLimite = f.format(data.getTime());
		String sql = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] atvArray = { "caminhada", "corrida", "ciclismo", "natacao" };
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  for ( String atv : atvArray ) {
			
			switch(atv) {
			
			case "caminhada" :
			case "ciclismo" :
			case "corrida" :
			  sql = "SELECT * FROM t_htk_" + atv 
			     + " INNER JOIN t_htk_atividade ON (t_htk_" + atv +".cd_atividade = t_htk_atividade.cd_atividade)"
			     + " INNER JOIN t_htk_ritmo_atv ON (t_htk_atividade.cd_ritmo = t_htk_ritmo_atv.cd_ritmo)"
			     + " WHERE (t_htk_atividade.dt_inicio >= ? AND t_htk_atividade.dt_inicio < ?)"
			     + " AND t_htk_atividade.cd_usuario = ?";
			  break;
			
			case "natacao" :
			  sql = "SELECT * FROM t_htk_" + atv 
		         + " INNER JOIN t_htk_atividade ON (t_htk_" + atv +".cd_atividade = t_htk_atividade.cd_atividade)"
		         + " INNER JOIN t_htk_ritmo_atv ON (t_htk_atividade.cd_ritmo = t_htk_ritmo_atv.cd_ritmo)"
		         + " INNER JOIN t_htk_estilo_natacao ON (t_htk_" + atv + ".cd_estilo = t_htk_estilo_natacao.cd_estilo)"
		         + " WHERE (t_htk_atividade.dt_inicio >= ? AND t_htk_atividade.dt_inicio < ?)"
		         + " AND t_htk_atividade.cd_usuario = ?";
			  break;
			}
				  
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, dia);
			pstmt.setString(2, diaLimite);
			pstmt.setInt(3, codigoCliente);
			rs = pstmt.executeQuery();
					  
			while(rs.next()) {
			  lista.add(getAtividade(rs, atv));
			}
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
		return lista;
		
	}
	
	@Override
	public void excluir(int codigoAtividade) {
	  PreparedStatement pstmt = null;
	  String sql = "DELETE FROM t_htk_atividade WHERE cd_atividade = ?";
	  
	  conexao = ConnectionManager.getInstance().getConnection();
	  
	  try {
	    pstmt = conexao.prepareStatement(sql);
	    pstmt.setInt(1, codigoAtividade);
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
	public Atividade buscar(int codigoAtividade, String atividade) {
	  Atividade atv = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String sql = null;
	  
	  switch(atividade) {
	  
	  case "caminhada" :
	  case "ciclismo" :
	  case "corrida" :
	    sql = "SELECT * FROM t_htk_atividade " +
		  	  "INNER JOIN t_htk_" + atividade + 
		  	  " ON (t_htk_atividade.cd_atividade = t_htk_" + atividade + ".cd_atividade) " +
		  	  "INNER JOIN t_htk_ritmo_atv ON (t_htk_atividade.cd_ritmo = t_htk_ritmo_atv.cd_ritmo) " +
		  	  "WHERE t_htk_atividade.cd_atividade = ?";
	    break;
	  
	  case "natacao" :
	    sql = "SELECT * FROM t_htk_atividade " +
		  	  "INNER JOIN t_htk_" + atividade + 
		  	  " ON (t_htk_atividade.cd_atividade = t_htk_" + atividade + ".cd_atividade) " +
		  	  "INNER JOIN t_htk_ritmo_atv ON (t_htk_atividade.cd_ritmo = t_htk_ritmo_atv.cd_ritmo) " +
		  	  "INNER JOIN t_htk_estilo_natacao ON (t_htk_" + atividade + ".cd_estilo = t_htk_estilo_natacao.cd_estilo) " +
		  	  "WHERE t_htk_atividade.cd_atividade = ?";
	    break;
	  
	  }
	  
	  
	  conexao = ConnectionManager.getInstance().getConnection();
	  
	  try {
	    pstmt = conexao.prepareStatement(sql);
	    pstmt.setInt(1, codigoAtividade);
	    rs = pstmt.executeQuery();
	    
	    if (rs.next()) {
	      atv = getAtividade(rs, atividade);
	    }
	    
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return atv;

	}
	
	private Atividade getAtividade(ResultSet rs, String atv) {
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		Atividade atividade = null;
		
		try {
		  dataInicio.setTimeInMillis(rs.getDate("DT_INICIO").getTime());
		  dataFim.setTimeInMillis(rs.getDate("DT_FIM").getTime());
		  
		  switch (atv) {
		  case "caminhada" :
		    atividade = new Caminhada(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, null, 
		    				rs.getDouble("VL_DISTANCIA"), new RitmoAtividade(rs.getInt("CD_RITMO"), 
		    				rs.getString("NM_RITMO")));
		    atividade.setTitulo(StringUtils.capitalize(atv));
		    break;
		  
		  case "corrida" :
			atividade = new Corrida(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, null, 
	    				rs.getDouble("VL_DISTANCIA"), new RitmoAtividade(rs.getInt("CD_RITMO"), 
	    				rs.getString("NM_RITMO")));
			atividade.setTitulo(StringUtils.capitalize(atv));
			break;
		  
		  case "ciclismo" :
			atividade = new Ciclismo(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, null, 
	    							 rs.getDouble("VL_DISTANCIA"), new RitmoAtividade(rs.getInt("CD_RITMO"), 
	    							 rs.getString("NM_RITMO")));
			atividade.setTitulo(StringUtils.capitalize(atv));
			break;
		  
		  case "natacao" :
			atividade = new Natacao(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, null,
					                new EstiloNatacao(rs.getInt("CD_ESTILO"), rs.getString("NM_ESTILO")), 
									new RitmoAtividade(rs.getInt("CD_RITMO"), rs.getString("NM_RITMO")));
			atividade.setTitulo(StringUtils.capitalize(atv));
			break;
		  }
		  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return atividade;
	}
	
}
