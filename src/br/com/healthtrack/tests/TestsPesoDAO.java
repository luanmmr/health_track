package br.com.healthtrack.tests;

import java.util.List;

import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.PesoDAO;

public class TestsPesoDAO {
	
	public static void main(String[] args) {
	
		PesoDAO dao = DAOFactory.getPesoDAO();
		Usuario usuario = new Usuario(1);
		usuario.setPeso(100);
		System.out.println("Cadastrou Peso");
		
		Peso peso = dao.buscar(3);
		System.out.println("Buscou");
		
		peso.setPeso(120.34);
		dao.atualizar(peso);
		System.out.println("Atualizou");
		
		List<Peso> lista = dao.listar();
		System.out.printf("Recuperou tudo. Tamanho lista %d\n", lista.size());
		
		dao.remover(3);
		System.out.println("Removeu");
	
	}
}
