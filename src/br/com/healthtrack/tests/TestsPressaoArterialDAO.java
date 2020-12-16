package br.com.healthtrack.tests;

import java.util.List;

import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.PressaoArterial;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.PressaoArterialDAO;

public class TestsPressaoArterialDAO {

	public static void main(String[] args) {
		
		PressaoArterialDAO dao = DAOFactory.getPressaoArterialDAO();
		Usuario usuario = new Usuario(1);
		usuario.setPressaoArterial(120, 60);
		System.out.println("Cadastrou Pressão");
		
		PressaoArterial pressao = dao.buscar(1);
		System.out.println("Buscou");
		
		pressao.setSistolica(20);
		pressao.setDiastolica(20);
		dao.atualizar(pressao);
		System.out.println("Atualizou");
		
		List<PressaoArterial> lista = dao.listar();
		System.out.printf("Recuperou tudo. Tamanho lista %d\n", lista.size());
		
		dao.remover(1);
		System.out.println("Removeu");

	}

}
