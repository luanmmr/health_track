package br.com.healthtrack.dao;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.exception.DBException;

public interface AtividadeDAO {
	
	void cadastrar(Atividade atividade) throws DBException;
	List<Atividade> listaAtividadesDia(int codigoCliente, Calendar data);
	void excluir(int codigoAtividade);
	Atividade buscar(int codigoAtividade, String atividade);
	void update(Atividade atividade) throws DBException;
	
}
