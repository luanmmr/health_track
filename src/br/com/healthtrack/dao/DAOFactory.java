package br.com.healthtrack.dao;

import br.com.healthtrack.dao.impl.OracleAlimentoDAO;
import br.com.healthtrack.dao.impl.OracleAtividadeDAO;
import br.com.healthtrack.dao.impl.OracleGrupoAlimentoDAO;
import br.com.healthtrack.dao.impl.OracleIngestaoDAO;
import br.com.healthtrack.dao.impl.OracleMedidaDAO;
import br.com.healthtrack.dao.impl.OraclePeriodoRefeicaoDAO;
import br.com.healthtrack.dao.impl.OraclePesoDAO;
import br.com.healthtrack.dao.impl.OraclePressaoArterialDAO;
import br.com.healthtrack.dao.impl.OracleUsuarioDAO;

public class DAOFactory {
	
	
	public static AlimentoDAO getAlimentoDAO() {
		return new OracleAlimentoDAO();
	}
	
	public static AtividadeDAO getAtividadeDAO() {
		return new OracleAtividadeDAO();
	}
	
	public static IngestaoDAO getIngestaoDAO() {
		return new OracleIngestaoDAO();
	}
	
	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	public static PressaoArterialDAO getPressaoArterialDAO() {
		return new OraclePressaoArterialDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static GrupoAlimentoDAO getGrupoAlimentoDAO() {
		return new OracleGrupoAlimentoDAO();
	}
	
	public static MedidaDAO getMedidaDAO() {
		return new OracleMedidaDAO();
	}
	
	public static PeriodoRefeicaoDAO getPeriodoRefeicaoDAO() {
		return new OraclePeriodoRefeicaoDAO();
	}

}
