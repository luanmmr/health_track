package br.com.healthtrack.bean;

import java.io.Serializable;

public class GrupoAlimento implements Serializable {
	
	private int codigo;
	private String nomeGrupo;
	
	public GrupoAlimento(int codigo, String nomeGrupo) {
		super();
		this.codigo = codigo;
		this.nomeGrupo = nomeGrupo;
	}
	
	public GrupoAlimento(int codigo) {
		super();
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}
	
	
}
