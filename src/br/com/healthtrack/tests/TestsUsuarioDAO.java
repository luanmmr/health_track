package br.com.healthtrack.tests;

import java.util.Calendar;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.UsuarioDAO;

public class TestsUsuarioDAO {

	public static void main(String[] args) {
		UsuarioDAO dao = DAOFactory.getUsuarioDAO();
		
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(2020, 01, 29);
		dao.cadastrar(new Usuario(0, "Luan", "Ribero", "luan.mesquita@hotmail.com", "celteamo1", 
								  dataNascimento, 1.70, 98, 140, 80));
		System.out.println("Cadastro feito");
		
		
		Usuario usuario = dao.buscar(32);
		System.out.println("Buscou");
		
		
		usuario.setEmail("luan.mm.ribeiro@gmail.com");
		dao.atualizar(usuario);
		System.out.println("Atualizou");
		

	}

}
