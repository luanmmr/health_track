package br.com.healthtrack.tests;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.PeriodoRefeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.IngestaoDAO;

public class TestsIngestaoDAO {

	public static void main(String[] args) {
		IngestaoDAO dao = DAOFactory.getIngestaoDAO();
		
		Ingestao ingestao = new Ingestao(0, new Usuario(1), new Alimento(2), new PeriodoRefeicao(1), 
										Calendar.getInstance(), 1);

		dao.cadastrar(ingestao);
		System.out.println("Cadastrou");
		
		ingestao = dao.buscar(3);
		System.out.println("Buscou");
		
		ingestao.getPeriodoRefeicao().setCodigo(2);
		dao.atualizar(ingestao);
		System.out.println("Atualizou");
		
		List<Ingestao> lista = dao.listar();
		System.out.printf("Recuperou tudo. Tamanho lista %d\n", lista.size());
		
		dao.remover(1);
		System.out.println("Remover");

	}

}
