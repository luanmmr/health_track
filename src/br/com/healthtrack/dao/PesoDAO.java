package br.com.healthtrack.dao;

import java.util.List;
import br.com.healthtrack.bean.Peso;

public interface PesoDAO {
	
	void cadastrar(Peso peso);
	void atualizar(Peso peso);
	void remover(int codigo);
	Peso buscar(int codigo);
	List<Peso> listar();

}
