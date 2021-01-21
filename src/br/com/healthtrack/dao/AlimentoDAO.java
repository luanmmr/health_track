package br.com.healthtrack.dao;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

public interface AlimentoDAO {
	
	void cadastrar(Alimento alimento) throws DBException;
	void atualizar(Alimento alimento);
	void remover(int codigo);
	Alimento buscar(int codigo);
	List<Alimento> listar();
}
