package br.com.healthtrack.bean;

import java.io.Serializable;

public class RitmoAtividade implements Serializable {
	
	private int codigo;
	private String nomeRitmo;
	
	
	public RitmoAtividade(int codigo, String nomeRitmo) {
		super();
		this.codigo = codigo;
		this.nomeRitmo = nomeRitmo;
	}
	
	
	public RitmoAtividade(int codigo) {
		super();
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNomeRitmo() {
		return nomeRitmo;
	}
	
	public void setNomeRitmo(String nomeRitmo) {
		this.nomeRitmo = nomeRitmo;
	}
	
}
