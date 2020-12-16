package br.com.healthtrack.dao;

import java.util.List;
import br.com.healthtrack.bean.Ingestao;

public interface IngestaoDAO {
	
	void cadastrar(Ingestao ingestao);
	void atualizar(Ingestao ingestao);
	void remover(int codigo);
	Ingestao buscar(int codigo);
	List<Ingestao> listar();

}
