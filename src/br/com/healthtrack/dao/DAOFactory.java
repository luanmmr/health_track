package br.com.healthtrack.dao;

public class DAOFactory {
	
	
	public static AlimentoDAO getAlimentoDAO() {
		return new OracleAlimentoDAO();
	}
	
	public static AtividadeDAO getAtividadeDAO() {
		return new OracleAtividadeDAO();
	}
	
	public static IngestaoDAO getIngestaoDAO() {
		return new OracleIngestao();
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

}
