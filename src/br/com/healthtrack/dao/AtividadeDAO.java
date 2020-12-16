package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Atividade;

public interface AtividadeDAO {
	
	void cadastrar(Atividade atividade);
	void atualizar(Atividade atividade);
	void remover(int codigo);
	Atividade buscar(int codigo);
	List<Atividade> listar(int codigoCliente);

}
