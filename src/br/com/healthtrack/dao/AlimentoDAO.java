package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.Alimento;

public interface AlimentoDAO {
	
	void cadastrar(Alimento alimento);
	void atualizar(Alimento alimento);
	void remover(int codigo);
	Alimento buscar(int codigo);
	List<Alimento> listar();
	
}
