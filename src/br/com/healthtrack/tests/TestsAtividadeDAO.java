package br.com.healthtrack.tests;

import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.Caminhada;
import br.com.healthtrack.bean.RitmoAtividade;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.dao.DAOFactory;

public class TestsAtividadeDAO {

	public static void main(String[] args) {
		
		AtividadeDAO dao = DAOFactory.getAtividadeDAO();
		
		Atividade atv = new Caminhada(0, Calendar.getInstance(), Calendar.getInstance(), 
									  new Usuario(1), 50, new RitmoAtividade(1));
		
		dao.cadastrar(atv);
		System.out.println("Cadastrou");

		atv = dao.buscar(7);
		System.out.printf("Recuperou. Código atividade: %d\n", atv.getCodigo());
		
		Caminhada atv2 = (Caminhada) atv;
		atv2.setDistancia(100);
		atv2.getRitmo().setCodigo(3);
		
		dao.atualizar(atv2);
		System.out.println("Atualizou");
		
		List<Atividade> lista = dao.listar(1);
		System.out.printf("Recuperou tudo. Tamanho lista %d\n", lista.size());
		
		dao.remover(8);
		System.out.println("Removeu");
		

	}

}
