package br.com.healthtrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.PressaoArterial;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;
import br.com.healthtrack.util.CriptografiaSenha;

public class OracleUsuarioDAO implements UsuarioDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Usuario usuario) throws DBException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO t_htk_usuario "
				   + "VALUES (SQ_HTK_USUARIO.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getSobrenome());
			pstmt.setString(3, usuario.getEmail().toLowerCase());
			pstmt.setString(4, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			pstmt.setDate(5, data);
			pstmt.setDouble(6, usuario.getPeso());
			pstmt.setDouble(7, usuario.getAltura());
			pstmt.setInt(8, usuario.getSistolica());
			pstmt.setInt(9, usuario.getDiastolica());
			pstmt.setDouble(10, usuario.getImc());
			pstmt.setInt(11, usuario.getMetaGastoCalorico());
			pstmt.executeUpdate();
			
			registrosPosCadastro(usuario);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar usuário");
			
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
	public void atualizar(Usuario usuario) throws DBException {
		PreparedStatement pstmt = null;
		String sql = "UPDATE t_htk_usuario SET NM_USUARIO = ?, NM_SOBRENOME = ?, DS_EMAIL = ?, "
				   + "PWD_SENHA = ?, DT_NASCIMENTO = ?, VL_META_KCAL = ?, VL_PESO = ?, VL_IMC = ?, "
				   + "VL_SISTOLICA = ?, VL_DIASTOLICA = ? "
				   + "WHERE CD_USUARIO = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getSobrenome());
			pstmt.setString(3, usuario.getEmail().toLowerCase());
			pstmt.setString(4, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			pstmt.setDate(5, data);
			pstmt.setInt(6, usuario.getMetaGastoCalorico());
			pstmt.setDouble(7, usuario.getPeso());
			pstmt.setDouble(8, usuario.getImc());
			pstmt.setInt(9, usuario.getSistolica());
			pstmt.setInt(10, usuario.getDiastolica());
			pstmt.setInt(11, usuario.getCodigo());
	
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar usuário");
			
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
	public boolean autenticar(String email, String senha) {
		boolean autenticou = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM t_htk_usuario WHERE DS_EMAIL = ? AND PWD_SENHA = ?";
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, email.toLowerCase());
			pstmt.setString(2, CriptografiaSenha.criptografar(senha));
			rs = pstmt.executeQuery();
			
			autenticou = rs.next();
			
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
		
		return autenticou;
	}
	
	@Override
	public Usuario buscarUsuario(String email) {
	  Usuario usuario = null;
	  PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM t_htk_usuario WHERE ds_email = ?";
      
      conexao = ConnectionManager.getInstance().getConnection();
      
      try {
        pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, email);
        rs = pstmt.executeQuery();
        
        if(rs.next()) {
          Calendar data = Calendar.getInstance();
          data.setTimeInMillis(rs.getDate("DT_NASCIMENTO").getTime());
          usuario = new Usuario(rs.getInt("CD_USUARIO"), rs.getString("NM_USUARIO"), 
        		  rs.getString("NM_SOBRENOME"), email, " ", data, 
        		  rs.getDouble("VL_ALTURA"), rs.getDouble("VL_PESO"), 
        		  rs.getInt("VL_SISTOLICA"), rs.getInt("VL_DIASTOLICA"),
        		  rs.getInt("VL_META_KCAL"));
        }
        
      } catch (SQLException e) {
    	  e.printStackTrace();
    	  
      } finally {
    	  try {
    	    pstmt.close();
    	    rs.close();
    	    conexao.close();
    	    
    	  } catch(SQLException e) {
    		  e.printStackTrace();
    	  }
      }
      
      return usuario;
	}
	
	private void registrosPosCadastro(Usuario usuario) {
		  int cdUsuario = 0;
		  Statement stmt = null;
		  ResultSet rs = null;
		  String sql = "SELECT MAX(cd_usuario) \"cd_usuario\" FROM t_htk_usuario";
		  
		  try {
		    stmt = conexao.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			cdUsuario = rs.getInt("CD_USUARIO");
			
			new OraclePesoDAO().cadastrar(
					new Peso(0, new Usuario(cdUsuario), Calendar.getInstance(), usuario.getPeso()));
			new OraclePressaoArterialDAO().cadastrar(
					new PressaoArterial(0, new Usuario(cdUsuario), Calendar.getInstance(), 
				     								   usuario.getSistolica(), usuario.getDiastolica()));
			
		  } catch (SQLException e) {
			  e.printStackTrace();
			  
		  } finally {
			  try {
			    stmt.close();
			    rs.close();
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
		  }
		}

}
