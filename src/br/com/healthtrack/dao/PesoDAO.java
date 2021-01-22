package br.com.healthtrack.dao;

import java.util.Calendar;
import java.util.List;
import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.Usuario;

public interface PesoDAO {
	
	void cadastrar(Peso peso);
	void atualizar(Peso peso);
	void remover(int codigo);
	Peso buscar(int codigo);
	List<Peso> listarUsuario(Usuario usuario);
	List<Peso> listarUsuario(Usuario usuario, Calendar data);
	
}
