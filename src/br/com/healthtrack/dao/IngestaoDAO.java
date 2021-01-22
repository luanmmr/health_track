package br.com.healthtrack.dao;

import java.util.Calendar;
import java.util.List;
import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

public interface IngestaoDAO {
	
	void cadastrar(Ingestao ingestao) throws DBException;
	void atualizar(Ingestao ingestao);
	void remover(int codigo);
	Ingestao buscar(int codigo);
	List<Ingestao> listar();
	List<Ingestao> listarDia(Usuario usuario, Calendar data);

}
