package br.com.healthtrack.dao.impl;

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
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;
import java.util.Map;

public class OracleAtividadeDAO implements AtividadeDAO {
	
	private Connection conexao;
	private String[] atvArray = { "caminhada", "corrida", "ciclismo", "natacao" };
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	@Override
	public void cadastrar(Atividade atividade) throws DBException {
		sql = "INSERT INTO t_htk_atividade "
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
	  sql = "UPDATE t_htk_atividade SET cd_ritmo = ?, dt_inicio = ?, dt_fim = ?, vl_kcal = ?"
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
	public List<Atividade> listaAtividadesDia(Usuario usr, Calendar data) {
		List<Atividade> lista = new ArrayList<Atividade>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String dia = f.format(data.getTime());
		data.add(Calendar.DATE, 1);
		String diaLimite = f.format(data.getTime());
		
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
			     + " AND t_htk_atividade.cd_usuario = ?"
			     + " ORDER BY t_htk_atividade.cd_atividade";
			  break;
			
			case "natacao" :
			  sql = "SELECT * FROM t_htk_" + atv 
		         + " INNER JOIN t_htk_atividade ON (t_htk_" + atv +".cd_atividade = t_htk_atividade.cd_atividade)"
		         + " INNER JOIN t_htk_ritmo_atv ON (t_htk_atividade.cd_ritmo = t_htk_ritmo_atv.cd_ritmo)"
		         + " INNER JOIN t_htk_estilo_natacao ON (t_htk_" + atv + ".cd_estilo = t_htk_estilo_natacao.cd_estilo)"
		         + " WHERE (t_htk_atividade.dt_inicio >= ? AND t_htk_atividade.dt_inicio < ?)"
		         + " AND t_htk_atividade.cd_usuario = ?"
		         + " ORDER BY t_htk_atividade.cd_atividade";
			  break;
			}
				  
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, dia);
			pstmt.setString(2, diaLimite);
			pstmt.setInt(3, usr.getCodigo());
			rs = pstmt.executeQuery();
					  
			while(rs.next()) {
			  lista.add(getAtividade(rs, atv, usr));
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
		    data.add(Calendar.DATE, -1);
		}
		return lista;
		
	}
	
	@Override
	public double caloriasPerdidasDia(Usuario usr, Calendar data) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		double caloriasPerdidas = 0;
		sql = "SELECT * FROM t_htk_atividade "
			+ "WHERE TO_CHAR(dt_inicio, 'DD/MM/YYYY') = ? AND cd_usuario = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  pstmt = conexao.prepareStatement(sql);
		  pstmt.setString(1, f.format(data.getTime()));
		  pstmt.setInt(2, usr.getCodigo());
		  rs = pstmt.executeQuery();
		  
		  while (rs.next()) {
			  caloriasPerdidas += rs.getDouble("vl_kcal");
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
		
		return caloriasPerdidas;
	}
	
	@Override
	public void excluir(int codigoAtividade) {
	  sql = "DELETE FROM t_htk_atividade WHERE cd_atividade = ?";
	  
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
	public Atividade buscar(int codigoAtividade, String atividade, Usuario usr) {
	  Atividade atv = null;
	  
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
	      atv = getAtividade(rs, atividade, usr);
	    }
	    
	  } catch (SQLException e) {
		  e.printStackTrace();
		  
	  } finally {
		  try {
		    pstmt.close();
		    conexao.close();
		    rs.close();
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  return atv;
	}
	
	@Override
	public int quantAtvMes(String dataMMyy, Usuario usuario) {
		int quantidade = 0;
		sql = "SELECT COUNT(*) FROM t_htk_atividade "
		    + "WHERE TO_CHAR(dt_inicio, 'MM/YY') = ? AND cd_usuario = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
		  pstmt = conexao.prepareStatement(sql);
		  pstmt.setString(1, dataMMyy);
		  pstmt.setInt(2, usuario.getCodigo());
		  rs = pstmt.executeQuery();
		  
		  if (rs.next()) {
			  quantidade = rs.getInt("COUNT(*)");
		  }
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
			  pstmt.close();
			  rs.close();
			  conexao.close();
				
			} catch (SQLException e ) {
				e.printStackTrace();
			}
		}
		
	  return quantidade;
	}
	
	@Override
	public Map<String, Integer> atvSegmento(String dataMMyy, Usuario usuario) {
	  Map<String, Integer> atvsSegmentMes = new HashMap<String, Integer>();
	  
	  conexao = ConnectionManager.getInstance().getConnection();
		
	  try {
	    for ( String atv : atvArray ) {
	      sql = "SELECT COUNT(*) FROM t_htk_" + atv + " "
			  + "INNER JOIN t_htk_atividade ON (t_htk_atividade.cd_atividade = t_htk_" + atv + ".cd_atividade) "
			  + "WHERE TO_CHAR(t_htk_atividade.dt_inicio, 'MM/YY') = ? AND cd_usuario = ?";
		  
	      pstmt = conexao.prepareStatement(sql);
		  pstmt.setString(1, dataMMyy);
		  pstmt.setInt(2, usuario.getCodigo());
		  rs = pstmt.executeQuery();
			  
		  if (rs.next() &&  rs.getInt("COUNT(*)") != 0) {
			  switch(atv) {
			  case "caminhada" :
			    atvsSegmentMes.put("Caminhada", rs.getInt("COUNT(*)"));
			    break;
			  
			  case "corrida" :
				atvsSegmentMes.put("Corrida", rs.getInt("COUNT(*)"));
				break;
			  
			  case "ciclismo" :
				atvsSegmentMes.put("Ciclismo", rs.getInt("COUNT(*)"));
				break;
			  
			  case "natacao" :
				atvsSegmentMes.put("Natação", rs.getInt("COUNT(*)"));
				break;
			  
			  default :
				atvsSegmentMes.put(atv, rs.getInt("COUNT(*)"));
			  }
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
		
		return atvsSegmentMes;
	}
	
	private Atividade getAtividade(ResultSet rs, String atv, Usuario usr) {
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		Atividade atividade = null;
		
		try {
		  dataInicio.setTimeInMillis(rs.getDate("DT_INICIO").getTime());
		  dataFim.setTimeInMillis(rs.getDate("DT_FIM").getTime());
		  
		  switch (atv) {
		  case "caminhada" :
		    atividade = new Caminhada(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, usr, 
		    				rs.getDouble("VL_DISTANCIA"), new RitmoAtividade(rs.getInt("CD_RITMO"), 
		    				rs.getString("NM_RITMO")));
		    atividade.setTitulo(StringUtils.capitalize(atv));
		    break;
		  
		  case "corrida" :
			atividade = new Corrida(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, usr, 
	    				rs.getDouble("VL_DISTANCIA"), new RitmoAtividade(rs.getInt("CD_RITMO"), 
	    				rs.getString("NM_RITMO")));
			atividade.setTitulo(StringUtils.capitalize(atv));
			break;
		  
		  case "ciclismo" :
			atividade = new Ciclismo(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, usr, 
	    							 rs.getDouble("VL_DISTANCIA"), new RitmoAtividade(rs.getInt("CD_RITMO"), 
	    							 rs.getString("NM_RITMO")));
			atividade.setTitulo(StringUtils.capitalize(atv));
			break;
		  
		  case "natacao" :
			atividade = new Natacao(rs.getInt("CD_ATIVIDADE"), dataInicio, dataFim, usr,
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
