package br.com.healthtrack.dao;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.exception.DBException;

public interface UsuarioDAO {
	
	void cadastrar(Usuario usuario) throws DBException;
	void atualizar(Usuario usuario) throws DBException;
	Usuario buscarUsuario(String email);
	boolean autenticar(String email, String senha);

}
