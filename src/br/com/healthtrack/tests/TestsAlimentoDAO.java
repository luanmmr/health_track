package br.com.healthtrack.tests;

import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.GrupoAlimento;
import br.com.healthtrack.bean.Medida;
import br.com.healthtrack.dao.AlimentoDAO;
import br.com.healthtrack.dao.DAOFactory;

public class TestsAlimentoDAO {

	public static void main(String[] args) {
		
		AlimentoDAO dao = DAOFactory.getAlimentoDAO();
		Alimento alimento = new Alimento(0, "Goiaba", new Medida(6), 1, 30, new GrupoAlimento(2));
		
		dao.cadastrar(alimento);
		System.out.println("Cadastrado");
		
		alimento = dao.buscar(1);
		System.out.println("Recuperado");
		
		alimento.setCarboidrato(200);
		dao.atualizar(alimento);
		System.out.println("Atualizado");
		
		List<Alimento> lista = dao.listar();
		System.out.println("Listados");
		
		dao.remover(1);
		System.out.println("Removido");
		
		
		
		
	}

}
