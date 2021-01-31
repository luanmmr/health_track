package br.com.healthtrack.dao;

import java.util.Calendar;
import java.util.Map;
import java.util.List;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

public interface AtividadeDAO {
	
	void cadastrar(Atividade atividade) throws DBException;
	List<Atividade> listaAtividadesDia(Usuario usr, Calendar data);
	void excluir(int codigoAtividade);
	Atividade buscar(int codigoAtividade, String atividade, Usuario usr);
	void update(Atividade atividade) throws DBException;
	int quantAtvMes(String dataMMyy, Usuario usuario);
	Map<String, Integer> atvSegmento(String dataMMyy, Usuario usuario);
	double caloriasPerdidasDia(Usuario usr, Calendar data);
	
}
