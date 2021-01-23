package br.com.healthtrack.dao;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.PressaoArterial;
import br.com.healthtrack.bean.Usuario;

public interface PressaoArterialDAO {
	
	void cadastrar(PressaoArterial pressaoArterial);
	void atualizar(PressaoArterial pressaoArterial);
	void remover(int codigo);
	PressaoArterial buscar(int codigo);
	List<PressaoArterial> listarUsuario(Usuario usuario);
	List<PressaoArterial> listarUsuario(Usuario usuario, Calendar data);

}
