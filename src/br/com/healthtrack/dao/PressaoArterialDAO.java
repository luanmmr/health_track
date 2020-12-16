package br.com.healthtrack.dao;

import java.util.List;

import br.com.healthtrack.bean.PressaoArterial;

public interface PressaoArterialDAO {
	
	void cadastrar(PressaoArterial pressaoArterial);
	void atualizar(PressaoArterial pressaoArterial);
	void remover(int codigo);
	PressaoArterial buscar(int codigo);
	List<PressaoArterial> listar();

}
